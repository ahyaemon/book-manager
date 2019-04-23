<template lang='pug'>
  v-app
    v-toolbar(app)
      v-toolbar-items
        v-btn(icon, @click="jumpToGithub")
          v-img(:src="require('./assets/github-brands.svg')", contain, height="34")
      v-toolbar-title
        div.mt-1 BOOK MANAGER
      v-spacer
    v-content
      v-container.container(fluid)
        v-layout(row wrap)
          // 検索
          v-flex(pt-3, xs12)
            v-card(color="teal lighten-5")
              v-card-text.ma-0.pa-0
                v-container.container(fluid)
                  v-form(ref="searchForm", lazy-validation)
                    v-layout(row wrap justify-space-around)
                      v-flex(xs5, color="grey")
                        v-text-field.body-1(label="タイトル", :counter="20", v-model="searchCondition.title")
                      v-flex(xs5)
                        v-autocomplete(:items="authorItems", clearable, label="著者", v-model="searchCondition.authorId")
                    v-layout(row wrap justify-space-around)
                      v-flex(xs5)
                        v-autocomplete(:items="publisherItems", clearable, label="出版社", v-model="searchCondition.publisherId")
                      v-flex(xs5)
                        v-btn.mb-5.mr-5(color='teal darken-3', dark, absolute, bottom, right, fab, @click="searchBooks")
                          v-icon search
          // 本の一覧表示
          transition-group.animation-container(name="list", tag="span")
            v-flex.list-item(pt-3, xs12, v-for="book in reversedBooks", :key="book.id")
              v-card.card(@click.stop="showUpdateDialog(book.id)")
                v-card-title
                  h1 {{ book.title }}
                v-card-text
                  div {{ book.author.name }}
                  div {{ book.publisher.name }}

      // 新規作成ボタン
      v-btn(color='black', dark, fixed, bottom, right, fab, @click.stop="showInitDbDialog()") !

      // DB 初期化ボタン
      v-btn.mr-5(color='pink', dark, fixed, bottom, right, fab, @click.stop="showCreateDialog()", style="margin-left: 20px") +

      // 新規作成ダイアログ
      v-dialog.create-dialog(v-model="createDialog", max-width=500)
        v-card
          v-card-title.headline.grey.lighten-2 新規登録
          v-card-text
            v-form(ref="createForm", lazy-validation)
              // タイトル
              v-text-field.pb-1(
                label="タイトル",
                required,
                :counter="20",
                v-model="newBook.title",
                :error="createDialogError.title.hasError",
                :error-messages="createDialogError.title.messages"
              )
              // 著者
              v-autocomplete(
                :items="authorItems",
                label="著者",
                v-model="newBook.author.id",
                v-if="!addNewAuthorCreate",
                :error="createDialogError.authorSelect.hasError",
                :error-messages="createDialogError.authorSelect.messages",
              )
              v-text-field(
                label="著者を直接入力",
                required,
                :counter="20",
                v-if="addNewAuthorCreate",
                v-model="newBook.author.name"
                :error="createDialogError.authorText.hasError",
                :error-messages="createDialogError.authorText.messages",
              )
              v-btn(depressed color="white" @click="noAuthorCreateClicked")
                v-icon.green--text.pr-1 help_outline
                span.green--text(v-if="!addNewAuthorCreate") 直接入力に変更（選択肢に無い場合）
                span.green--text(v-if="addNewAuthorCreate") 選択肢に変更
              // 出版社
              v-autocomplete(
                :items="publisherItems",
                label="出版社",
                v-model="newBook.publisher.id"
                v-if="!addNewPublisherCreate",
                :error="createDialogError.publisherSelect.hasError",
                :error-messages="createDialogError.publisherSelect.messages",
              )
              v-text-field(
                label="出版社を直接入力",
                required,
                :counter="20",
                v-if="addNewPublisherCreate",
                v-model="newBook.publisher.name"
                :error="createDialogError.publisherText.hasError",
                :error-messages="createDialogError.publisherText.messages",
              )
              v-btn(depressed color="white", @click="noPublisherCreateClicked")
                v-icon.green--text.pr-1 help_outline
                span.green--text(v-if="!addNewPublisherCreate") 直接入力に変更（選択肢に無い場合）
                span.green--text(v-if="addNewPublisherCreate") 選択肢に変更
              br
              // 登録ボタン
              v-btn(color="info", @click="createBook") 登録

      // 更新ダイアログ
      v-dialog.update-dialog(v-model="updateDialog", max-width=500)
        v-card
          v-card-title.headline.grey.lighten-2 更新
          v-card-text
            v-form
              // タイトル
              v-text-field.pb-1(
                label="タイトル",
                required,
                :counter="20",
                v-model="selectedBook.title",
                :error="updateDialogError.title.hasError",
                :error-messages="updateDialogError.title.messages"
              )
              // 著者
              v-autocomplete(
                :items="authorItems",
                label="著者",
                v-model="selectedBook.author.id",
                v-if="!addNewAuthorUpdate",
                :error="updateDialogError.authorSelect.hasError",
                :error-messages="updateDialogError.authorSelect.messages",
              )
              v-text-field(
                label="著者を直接入力",
                required,
                :counter="20",
                v-if="addNewAuthorUpdate",
                v-model="selectedBook.author.name"
                :error="updateDialogError.authorText.hasError",
                :error-messages="updateDialogError.authorText.messages",
              )
              v-btn(depressed color="white" @click="noAuthorUpdateClicked")
                v-icon.green--text.pr-1 help_outline
                span.green--text(v-if="!addNewAuthorUpdate") 直接入力に変更（選択肢に無い場合）
                span.green--text(v-if="addNewAuthorUpdate") 選択肢に変更
              // 出版社
              v-autocomplete(
                :items="publisherItems",
                label="出版社",
                v-model="selectedBook.publisher.id"
                v-if="!addNewPublisherUpdate",
                :error="updateDialogError.publisherSelect.hasError",
                :error-messages="updateDialogError.publisherSelect.messages",
              )
              v-text-field(
                label="出版社を直接入力",
                required,
                :counter="20",
                v-if="addNewPublisherUpdate",
                v-model="selectedBook.publisher.name"
                :error="updateDialogError.publisherText.hasError",
                :error-messages="updateDialogError.publisherText.messages",
              )
              v-btn(depressed color="white", @click="noPublisherUpdateClicked")
                v-icon.green--text.pr-1 help_outline
                span.green--text(v-if="!addNewPublisherUpdate") 直接入力に変更（選択肢に無い場合）
                span.green--text(v-if="addNewPublisherUpdate") 選択肢に変更
              br
              // 更新・削除ボタン
              v-btn(color="info", @click="updateBook") 更新
              v-btn(color="error", @click="deleteBook") 削除
      // DB 初期化ダイアログ
      v-dialog.init-db-dialog(v-model="initDbDialog", max-width=300)
        v-card
          v-card-text
            p DB 初期化しますか？
            v-btn(color="info", @click="initDb") YES
            v-btn(color="error", @click="initDbDialog = false") NO

</template>

<script lang='ts'>
    import { Component, Vue } from 'vue-property-decorator'

    import Book from '@/domain/entity/book'
    import Author from '@/domain/entity/author'
    import Publisher from '@/domain/entity/publisher'
    import BookFactory from '@/domain/factory/book_factory'

    import ApiError from '@/domain/vo/api_error'
    import DialogError from '@/domain/vo/dialog_error'
    import DialogErrorFactory from '@/domain/factory/dialog_error_factory'

    import SearchCondition from '@/domain/vo/search_condition'
    import SearchConditionFactory from '@/domain/factory/search_condition_factory'

    import axios from 'axios'

    @Component
    export default class App extends Vue {
      private books: Book[] = []
      private authors: Author[] = []
      private publishers: Publisher[] = []

      // 登録用
      private newBook: Book = BookFactory.default()
      private createDialog: boolean = false
      private addNewAuthorCreate: boolean = false
      private addNewPublisherCreate: boolean = false
      private createDialogError: DialogError = DialogErrorFactory.default()

      // 更新用
      private selectedBook: Book = BookFactory.default()
      private updateDialog: boolean = false
      private addNewAuthorUpdate: boolean = false
      private addNewPublisherUpdate: boolean = false
      private updateDialogError: DialogError = DialogErrorFactory.default()

      // 検索用
      private searchCondition: SearchCondition = SearchConditionFactory.default()

      // DB 初期化用
      private initDbDialog: boolean = false

      private mounted() {
        axios.get('/api/book/get').then((response) => {
          this.books = response.data
        })
        axios.get('/api/book/getAuthors').then((response) => {
          this.authors = response.data
        })
        axios.get('/api/book/getPublishers').then((response) => {
          this.publishers = response.data
        })
      }

      /**
       * 新規作成ダイアログを表示する。
       */
      private showCreateDialog() {
        this.createDialogError = DialogErrorFactory.default()
        this.createDialog = true
      }

      /**
       * 更新ダイアログを表示する。
       */
      private showUpdateDialog(id: number) {
        this.updateDialogError = DialogErrorFactory.default()
        const book = this.books.find((b) => b.id === id)!
        this.selectedBook = BookFactory.copy(book)
        this.updateDialog = true
      }

      /**
       * DB 初期化ダイアログを表示する。
       */
      private showInitDbDialog() {
        this.initDbDialog = true
      }

      /**
       * DB 初期化リクエストを送信する。
       */
      private async initDb() {
        await axios.post('/api/db/init')
        this.initDbDialog = false
        axios.get('/api/book/get').then((response) => {
          this.books = response.data
        })
        axios.get('/api/book/getAuthors').then((response) => {
          this.authors = response.data
        })
        axios.get('/api/book/getPublishers').then((response) => {
          this.publishers = response.data
        })
      }

      /**
       * 本を作成する。
       * 著者が存在しない場合は新規作成。
       * 出版社が存在しない場合は新規作成。
       */
      private async createBook() {
        if (this.addNewAuthorCreate) {
          this.newBook.author.id = null
        }

        if (this.addNewPublisherCreate) {
          this.newBook.publisher.id = null
        }

        await axios.post('/api/book/create', this.newBook).then((response: any) => {
          // バリデーションチェック
          const errors: any[] = response.data.errors
          if (errors.length > 0) {
            this.createDialogError =
                DialogErrorFactory.fromResponseErrors(errors, this.addNewAuthorCreate, this.addNewPublisherCreate)
            return
          }
          this.createDialog = false
        })

        axios.get('/api/book/get').then((response) => {
          this.books = response.data
        })

        // 著者を更新している場合は新たに取得
        if (this.addNewAuthorCreate) {
          axios.get('/api/book/getAuthors').then((response) => {
            this.authors = response.data
            this.addNewAuthorCreate = false
          })
        }

        // 出版社を更新している場合は新たに取得
        if (this.addNewPublisherCreate) {
          axios.get('/api/book/getPublishers').then((response) => {
            this.publishers = response.data
            this.addNewPublisherCreate = false
          })
        }
      }

      /**
       * 本を更新する。
       * 著者が存在しない場合は新規作成。
       * 出版社が存在しない場合は新規作成。
       */
      private async updateBook() {
        if (this.addNewAuthorUpdate) {
          this.selectedBook.author.id = null
        }

        if (this.addNewPublisherUpdate) {
          this.selectedBook.publisher.id = null
        }

        await axios.post('/api/book/update', this.selectedBook).then((response) => {
          // バリデーションチェック
          const errors: any[] = response.data.errors
          if (errors.length > 0) {
            this.updateDialogError =
                DialogErrorFactory.fromResponseErrors(errors, this.addNewAuthorUpdate, this.addNewPublisherUpdate)
            return
          }
          this.updateDialog = false
        })

        axios.get('/api/book/get').then((response) => {
          this.books = response.data
        })

        // 著者を更新している場合は新たに取得
        if (this.addNewAuthorUpdate) {
          axios.get('/api/book/getAuthors').then((response) => {
            this.authors = response.data
            this.addNewAuthorUpdate = false
          })
        }

        // 出版社を更新している場合は新たに取得
        if (this.addNewPublisherUpdate) {
          axios.get('/api/book/getPublishers').then((response) => {
            this.publishers = response.data
            this.addNewPublisherUpdate = false
          })
        }
      }

      /**
       * 検索条件に従って本を検索する。
       */
      private searchBooks() {
        axios.get('/api/book/search', { params: this.searchCondition }).then((response) => {
          this.books = response.data
        })
      }

      /**
       * 本を削除する。
       */
      private async deleteBook() {
        await axios.post('/api/book/delete', { id: this.selectedBook.id }).then((response) => {
          this.updateDialog = false
        })

        await axios.get('/api/book/get').then((response) => {
          this.books = response.data
        })
      }

      /**
       * vuetify の select 用に、authors を変換する。
       */
      get authorItems() {
        return this.authors.map((author) => {
          return {
            value: author.id,
            text: author.name,
          }
        })
      }

      /**
       * vuetify の select publishers を変換する。
       */
      get publisherItems() {
        return this.publishers.map((publisher) => {
          return {
            value: publisher.id,
            text: publisher.name,
          }
        })
      }

      /**
       * 新しい本が上に来るようにするため、リストを reverse する。
       */
      get reversedBooks() {
        return this.books.reverse()
      }

      /**
       * github へ遷移させる。
       */
      private jumpToGithub() {
        window.open('https://github.com/ahyaemon/book-manager', '_blank')
      }

      /**
       * 著者の入力方法を切り替える（新規作成ダイアログ）。
       */
      private noAuthorCreateClicked() {
        this.addNewAuthorCreate = !this.addNewAuthorCreate
      }

      /**
       * 出版社の入力方法を切り替える（新規作成ダイアログ）。
       */
      private noPublisherCreateClicked() {
        this.addNewPublisherCreate = !this.addNewPublisherCreate
      }

      /**
       * 著者の入力方法を切り替える（更新ダイアログ）。
       */
      private noAuthorUpdateClicked() {
        this.addNewAuthorUpdate = !this.addNewAuthorUpdate
      }

      /**
       * 出版社の入力方法を切り替える（更新ダイアログ）。
       */
      private noPublisherUpdateClicked() {
        this.addNewPublisherUpdate = !this.addNewPublisherUpdate
      }

    }
</script>

<style lang='scss'>
  body {
    font-family: 游ゴシック, YuGothic, "ヒラギノ角ゴ Pro", "Hiragino Kaku Gothic Pro", メイリオ, Meiryo, sans-serif;
  }

  .container {
    width: 1000px;

    .animation-container {
      width: 100%;

      .list-item {
        transition: all 0.3s;
      }
      .list-enter, .list-leave-to {
        opacity: 0;
        transform: translateX(30px);
      }
      .list-leave-active {
        position: absolute;
      }
    }
  }

</style>

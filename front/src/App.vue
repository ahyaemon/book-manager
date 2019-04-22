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
              v-card-text
                v-container.container(fluid)
                  v-form(ref="searchForm", lazy-validation)
                    v-layout(row wrap justify-space-around)
                      v-flex(xs5)
                        v-text-field.body-1(label="タイトル")
                      v-flex(xs5)
                        v-autocomplete(:items="authorItems", label="著者")
                    v-layout(row wrap justify-space-around)
                      v-flex(xs5)
                        v-autocomplete(:items="publisherItems", label="出版社")
                      v-flex(xs5)
                        v-btn.mb-5.mr-5(color='teal darken-3', dark, absolute, bottom, right, fab)
                          v-icon search
          // 本の一覧表示
          template(v-for="book in books")
            v-flex(pt-3, xs12)
              v-card(@click.stop="showUpdateDialog(book.id)")
                v-card-title
                  h1 {{ book.title }}
                v-card-text
                  div {{ book.author.name }}
                  div {{ book.publisher.name }}

      // 新規作成ボタン
      v-btn(color='pink', dark, fixed, bottom, right, fab, @click.stop="showCreateDialog()") +

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
              v-text-field(label="タイトル", required, :counter="20", v-model="selectedBook.title")
              // 著者
              v-autocomplete(:items="authorItems" label="著者を選択", v-model="selectedBook.author.id", v-if="!addNewAuthorUpdate")
              v-text-field(label="著者を直接入力", required, :counter="20" v-if="addNewAuthorUpdate", v-model="selectedBook.author.name")
              v-btn(depressed color="white", @click="noAuthorUpdateClicked")
                v-icon.green--text.pr-1 arrow_upward
                span.green--text(v-if="!addNewAuthorUpdate") 直接入力に変更（選択肢に無い場合）
                span.green--text(v-if="addNewAuthorUpdate") 選択肢に変更
              // 出版社
              v-autocomplete(:items="publisherItems", label="出版社を選択", v-model="selectedBook.publisher.id", v-if="!addNewPublisherUpdate")
              v-text-field(label="出版社を直接入力", required, :counter="20", v-if="addNewPublisherUpdate", v-model="selectedBook.publisher.name")
              v-btn(depressed color="white", @click="noPublisherUpdateClicked")
                v-icon.green--text.pr-1 arrow_upward
                span.green--text(v-if="!addNewPublisherUpdate") 直接入力に変更（選択肢に無い場合）
                span.green--text(v-if="addNewPublisherUpdate") 選択肢に変更
              br
              // 更新・削除ボタン
              v-btn(color="info", @click="updateBook") 更新
              v-btn(color="error", @click="deleteBook") 削除

</template>

<script lang='ts'>
    import { Component, Vue } from 'vue-property-decorator'

    import Book from '@/domain/entity/book'
    import Author from '@/domain/entity/author'
    import Publisher from '@/domain/entity/publisher'
    import BookFactory from '@/domain/factory/book_factory'

    import ApiError from '@/domain/vo/ApiError'
    import DialogError from '@/domain/vo/DialogError'
    import DialogErrorFactory from '@/domain/factory/dialog_error_factory'

    import axios from 'axios'

    @Component
    export default class App extends Vue {
      private books: Book[] = []
      private authors: Author[] = []
      private publishers: Publisher[] = []

      // 登録用
      private newBook: Book = BookFactory.createDefaultBook()
      private createDialog: boolean = false
      private addNewAuthorCreate: boolean = false
      private addNewPublisherCreate: boolean = false
      private createDialogError: DialogError = DialogErrorFactory.createDefaultDialogError()

      // 更新用
      private selectedBook: Book = BookFactory.createDefaultBook()
      private updateDialog: boolean = false
      private addNewAuthorUpdate: boolean = false
      private addNewPublisherUpdate: boolean = false

      private mounted() {
        axios.get('/api/book/initialize').then((response) => {
          this.books = response.data.books
          this.authors = response.data.authors
          this.publishers = response.data.publishers
        })
      }

      private showCreateDialog(id: number) {
        this.createDialogError = DialogErrorFactory.createDefaultDialogError()
        this.createDialog = true
      }

      private showUpdateDialog(id: number) {
        this.updateDialog = true
        const book = this.books.find((b) => b.id === id)!
        this.selectedBook = {
          id: book.id,
          title: book.title,
          author: {
            id: book.author.id,
            name: book.author.name,
          },
          publisher: {
            id: book.publisher.id,
            name: book.publisher.name,
          },
        }
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
          const errors: any[] = response.data.errors
          if (errors.length > 0) {
            // title
            const titleError = errors.find((error) => error.field === 'title')
            if (titleError) {
                this.createDialogError.title.hasError = true
                this.createDialogError.title.messages.push(titleError.message)
            } else {
                this.createDialogError.title.hasError = false
                this.createDialogError.title.messages = []
            }
            // author
            const authorError = errors.find((error) => error.field === 'author.validAuthorInput')
            if (authorError) {
              if (this.addNewAuthorCreate) {
                this.createDialogError.authorText.hasError = true
                this.createDialogError.authorText.messages.push(authorError.message)
              } else {
                this.createDialogError.authorSelect.hasError = true
                this.createDialogError.authorSelect.messages.push(authorError.message)
              }
            } else {
                this.createDialogError.authorText.hasError = false
                this.createDialogError.authorText.messages = []
                this.createDialogError.authorSelect.hasError = false
                this.createDialogError.authorSelect.messages = []
            }
            // publisher
            const publisherError = errors.find((error) => error.field === 'publisher.validPublisherInput')
            if (publisherError) {
              if (this.addNewPublisherCreate) {
                this.createDialogError.publisherText.hasError = true
                this.createDialogError.publisherText.messages.push(publisherError.message)
              } else {
                this.createDialogError.publisherSelect.hasError = true
                this.createDialogError.publisherSelect.messages.push(publisherError.message)
              }
            } else {
                this.createDialogError.publisherText.hasError = false
                this.createDialogError.publisherText.messages = []
                this.createDialogError.publisherSelect.hasError = false
                this.createDialogError.publisherSelect.messages = []
            }
            return
          }

          this.createDialog = false
        })

        await axios.get('/api/book/get').then((response) => {
          this.books = response.data
        })
      }

      private async updateBook() {
        if (this.addNewAuthorUpdate) {
          this.selectedBook.author.id = null
        }

        if (this.addNewPublisherUpdate) {
          this.selectedBook.publisher.id = null
        }

        await axios.post('/api/book/update', this.selectedBook).then((response) => {
          this.updateDialog = false
        })

        await axios.get('/api/book/get').then((response) => {
          this.books = response.data
        })
      }

      private async deleteBook() {
        await axios.post('/api/book/delete', { id: this.selectedBook.id }).then((response) => {
          this.updateDialog = false
        })

        await axios.get('/api/book/get').then((response) => {
          this.books = response.data
        })
      }

      get authorItems() {
        return this.authors.map((author) => {
          return {
            value: author.id,
            text: author.name,
          }
        })
      }

      get publisherItems() {
        return this.publishers.map((publisher) => {
          return {
            value: publisher.id,
            text: publisher.name,
          }
        })
      }

      private jumpToGithub() {
        window.open('https://github.com/ahyaemon/book-manager', '_blank')
      }

      private noAuthorCreateClicked() {
        this.addNewAuthorCreate = !this.addNewAuthorCreate
        this.newBook.author.id = null
        this.newBook.author.name = ''
      }

      private noPublisherCreateClicked() {
        this.addNewPublisherCreate = !this.addNewPublisherCreate
        this.newBook.publisher.id = null
        this.newBook.publisher.name = ''
      }

      private noAuthorUpdateClicked() {
        this.addNewAuthorUpdate = !this.addNewAuthorUpdate
      }

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
  }
</style>
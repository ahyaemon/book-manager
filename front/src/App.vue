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
      // 本の一覧表示
      v-container.container(fluid)
        v-layout(row wrap)
          template(v-for="book in books")
            v-flex(pt-3, xs12)
              v-card.card(@click.stop="showUpdateDialog(book.id)")
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
              v-text-field(label="タイトル", required, :counter="100" v-model="newBook.title")

              v-autocomplete(:items="authorItems", label="著者", v-model="newBook.author.id" v-if="!addNewAuthor")
              v-text-field(label="著者を直接入力", required, :counter="100" v-if="addNewAuthor" v-model="newBook.author.name")
              v-btn(depressed color="white" @click="noAuthorClicked")
                v-icon.green--text.pr-1 help_outline
                span.green--text 著者がいない？

              v-autocomplete(:items="publisherItems", label="出版社", v-model="newBook.publisher.id" v-if="!addNewPublisher")
              v-text-field(label="出版社を直接入力", required, :counter="100" v-if="addNewPublisher" v-model="newBook.publisher.name")
              v-btn(depressed color="white" @click="noPublisherClicked")
                v-icon.green--text.pr-1 help_outline
                span.green--text 出版社がない？

              br
              v-btn(color="info" @click="createBook") 登録

      // 更新ダイアログ
      v-dialog.update-dialog(v-model="updateDialog", max-width=500)
        v-card
          v-card-title.headline.grey.lighten-2 更新
          v-card-text
            v-form
              v-text-field(label="タイトル", required, :counter="100", v-model="selectedBook.title")
              v-autocomplete(:items="authorItems" label="著者", v-model="selectedBook.author.id")
              v-autocomplete(:items="publisherItems", label="出版社", v-model="selectedBook.publisher.id")
              v-btn(color="info") 更新
              v-btn(color="error") 削除

</template>

<script lang='ts'>
    import { Component, Vue } from 'vue-property-decorator'
    import Book from '@/domain/entity/book'
    import Author from '@/domain/entity/author'
    import Publisher from '@/domain/entity/publisher'
    import BookFactory from '@/domain/factory/book_factory'
    import axios from 'axios'

    @Component
    export default class App extends Vue {
      private books: Book[] = []
      private selectedBook: Book = BookFactory.createDefaultBook()
      private newBook: Book = BookFactory.createDefaultBook()
      private createDialog: boolean = false
      private updateDialog: boolean = false
      private addNewAuthor: boolean = false
      private addNewPublisher: boolean = false
      private authors: Author[] = []
      private publishers: Publisher[] = []

      private mounted() {
        axios.get('/api/book/get').then((response) => {
          this.books = response.data
        })

        axios.get('/api/author/get').then((response) => {
          this.authors = response.data
        })

        axios.get('/api/publisher/get').then((response) => {
          this.publishers = response.data
        })
      }

      private showCreateDialog(id: number) {
        this.createDialog = true
      }

      private showUpdateDialog(id: number) {
        this.updateDialog = true
        this.selectedBook = this.books.find((book) => book.id === id)!
      }

      private async createBook() {
        await axios.post('/api/book/create', this.newBook).then((response) => {
          this.createDialog = false
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

      private noAuthorClicked() {
        this.addNewAuthor = !this.addNewAuthor
        this.newBook.author.id = null
        this.newBook.author.name = ''
      }

      private noPublisherClicked() {
        this.addNewPublisher = !this.addNewPublisher
        this.newBook.publisher.id = null
        this.newBook.publisher.name = ''
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
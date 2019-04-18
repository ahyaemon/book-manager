<template lang='pug'>
  v-app
    v-toolbar(app)
      v-toolbar-title BOOK MANAGER
    v-content
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

      v-dialog.create-dialog(v-model="createDialog", max-width=500)
        v-card
          v-card-title.headline.grey.lighten-2 新規登録
          v-card-text
            v-form
              v-text-field(label="タイトル", required)
              v-select(:items="authorItems", label="著者")
              v-select(:items="publisherItems", label="出版社")
              v-btn(color="info") 登録

      v-dialog.update-dialog(v-model="updateDialog", max-width=500)
        v-card
          v-card-title.headline.grey.lighten-2 更新
          v-card-text
            v-form
              v-text-field(label="タイトル", required, :value="selectedBook.title")
              v-select(:items="authorItems" label="著者", v-model="selectedBook.author.id")
              v-select(:items="publisherItems", label="出版社", v-model="selectedBook.publisher.id")
              v-btn(color="info") 更新
              v-btn(color="error") 削除
      v-btn(color='pink', dark, fixed, bottom, right, fab, @click.stop="createDialog = true") +

</template>

<script lang='ts'>
    import { Component, Vue } from 'vue-property-decorator'
    import axios from 'axios'

    interface Book {
      id: number,
      title: string,
      author: Author,
      publisher: Publisher,
    }

    interface Author {
      id: number,
      name: string,
    }

    interface Publisher {
      id: number,
      name: string,
    }

    @Component
    export default class App extends Vue {
      private books: Book[] = []
      private selectedBook: Book = { id: 0, title: '', author: { id: 0, name: '' }, publisher: { id: 0, name: '' } }
      private createDialog: boolean = false
      private updateDialog: boolean = false
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

      private showUpdateDialog(id: number) {
        this.updateDialog = true
        this.selectedBook = this.books.find((book) => book.id === id)!
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
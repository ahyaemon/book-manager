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
                  div {{ book.author }}
                  div {{ book.publisher }}

      v-dialog.create-dialog(v-model="createDialog", max-width=500)
        v-card
          v-card-title.headline.grey.lighten-2 新規登録
          v-card-text
            v-form
              v-text-field(label="タイトル", required)
              v-select(:items="authors", label="著者")
              v-select(:items="publishers", label="出版社")
              v-btn(color="info") 登録

      v-dialog.update-dialog(v-model="updateDialog", max-width=500)
        v-card
          v-card-title.headline.grey.lighten-2 更新
          v-card-text
            v-form
              v-text-field(label="タイトル", required, :value="selectedBook.title")
              v-select(:items="authors", label="著者", :value="selectedBook.author")
              v-select(:items="publishers", label="出版社", :value="selectedBook.publisher")
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
      author: string,
      publisher: string,
    }

    @Component
    export default class App extends Vue {
      private books: Book[] = []
      private selectedBook: Book = { id: 0, title: '', author: '', publisher: '' }
      private createDialog: boolean = false
      private updateDialog: boolean = false
      private authors = [
        '夏目漱石',
        '太宰治',
        'Jim Blandy',
      ]
      private publishers = [
        '講談社',
        '集英社',
        'オライリージャパン',
      ]

      private mounted() {
        axios.get('/book/get').then((response) => {
          this.books = response.data
        })
      }

      private showUpdateDialog(id: number) {
        this.updateDialog = true
        this.selectedBook = this.books.find((book) => book.id === id)!
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
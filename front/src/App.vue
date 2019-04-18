<template lang='pug'>
  v-app
    v-toolbar(app)
      v-toolbar-title BOOK MANAGER
    v-content
      v-container.container(fluid)
        v-layout(row wrap)
          template(v-for="book in books")
            v-flex(pt-3, xs12)
              v-card.card
                v-card-title
                  h1 {{ book.title }}
                v-card-text
                  div {{ book.author }}
                  div {{ book.publisher }}

      v-btn(color='pink', dark, fixed, bottom, right, fab) +
</template>

<script lang='ts'>
    import { Component, Vue } from 'vue-property-decorator'

    @Component
    export default class App extends Vue {
      private books = [
        { title: '吾輩は猫である', author: '夏目漱石', publisher: '桃源社' },
        { title: 'プログラミングRust', author: 'Jim Blandy, Jason Orendorff他', publisher: '桃源社' },
        { title: 'Programming Rust: Fast, Safe Systems Development', author: '夏目漱石', publisher: '桃源社' },
        { title: '吾輩は猫である', author: '夏目漱石', publisher: '桃源社' },
        { title: '吾輩は猫である', author: '夏目漱石', publisher: '桃源社' },
        { title: '吾輩は猫である', author: '夏目漱石', publisher: '桃源社' },
        { title: '吾輩は猫である', author: '夏目漱石', publisher: '桃源社' },
        { title: '吾輩は猫である', author: '夏目漱石', publisher: '桃源社' },
        { title: '吾輩は猫である', author: '夏目漱石', publisher: '桃源社' },
      ]


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
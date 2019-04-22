import Book from '@/domain/entity/book'

export default class BookFactory {

  /**
   * デフォルトの Book を作成する。
   */
  public static default(): Book {
    return {
      id: null,
      title: '',
      author: {
        id: null,
        name: '',
      },
      publisher: {
        id: null,
        name: '',
      },
    }
  }

  /**
   * 受け取った book をコピーして新しい Book を返す。
   * @param book
   */
  public static copy(book: Book): Book {
    return {
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

}

import Book from '@/domain/entity/book'

export default class BookFactory {
  public static createDefaultBook(): Book {
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
}

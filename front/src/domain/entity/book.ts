import Author from '@/domain/entity/author'
import Publisher from '@/domain/entity/publisher'

export default interface Book {
  id: number | null,
  title: string,
  author: Author,
  publisher: Publisher,
}

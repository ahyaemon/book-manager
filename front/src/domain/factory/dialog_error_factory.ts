import ApiError from '@/domain/vo/ApiError'
import DialogError from '@/domain/vo/DialogError'

export default class DialogErrorFactory {

  /**
   * デフォルトの DialogError を作成する。
   */
  public static default(): DialogError {
    return {
      title: DialogErrorFactory.createDefaultApiError(),
      authorText: DialogErrorFactory.createDefaultApiError(),
      authorSelect: DialogErrorFactory.createDefaultApiError(),
      publisherText: DialogErrorFactory.createDefaultApiError(),
      publisherSelect: DialogErrorFactory.createDefaultApiError(),
    }
  }

  /**
   * errors から　DialogError を作成する。
   * @param errors
   * @param addNewAuthor
   * @param addNewPublisher
   */
  public static fromResponseErrors(errors: any[], addNewAuthor: boolean, addNewPublisher: boolean): DialogError {
    const dialogError = DialogErrorFactory.default()
    // title
    const titleError = errors.find((error) => error.field === 'title')
    if (titleError) {
        dialogError.title.hasError = true
        dialogError.title.messages.push(titleError.message)
    } else {
        dialogError.title.hasError = false
        dialogError.title.messages = []
    }
    // author
    const authorError = errors.find((error) => error.field === 'author.validAuthorInput')
    if (authorError) {
      if (addNewAuthor) {
        dialogError.authorText.hasError = true
        dialogError.authorText.messages.push(authorError.message)
      } else {
        dialogError.authorSelect.hasError = true
        dialogError.authorSelect.messages.push(authorError.message)
      }
    } else {
        dialogError.authorText.hasError = false
        dialogError.authorText.messages = []
        dialogError.authorSelect.hasError = false
        dialogError.authorSelect.messages = []
    }
    // publisher
    const publisherError = errors.find((error) => error.field === 'publisher.validPublisherInput')
    if (publisherError) {
      if (addNewPublisher) {
        dialogError.publisherText.hasError = true
        dialogError.publisherText.messages.push(publisherError.message)
      } else {
        dialogError.publisherSelect.hasError = true
        dialogError.publisherSelect.messages.push(publisherError.message)
      }
    } else {
        dialogError.publisherText.hasError = false
        dialogError.publisherText.messages = []
        dialogError.publisherSelect.hasError = false
        dialogError.publisherSelect.messages = []
    }
    return dialogError
  }

  private static createDefaultApiError(): ApiError {
    return {
      hasError: false,
      messages : [],
    }
  }

}

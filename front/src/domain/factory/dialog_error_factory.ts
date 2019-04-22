import ApiError from '@/domain/vo/ApiError'
import DialogError from '@/domain/vo/DialogError'

export default class DialogErrorFactory {
  public static createDefaultDialogError(): DialogError {
    return {
      title: DialogErrorFactory.createDefaultApiError(),
      authorText: DialogErrorFactory.createDefaultApiError(),
      authorSelect: DialogErrorFactory.createDefaultApiError(),
      publisherText: DialogErrorFactory.createDefaultApiError(),
      publisherSelect: DialogErrorFactory.createDefaultApiError(),
    }
  }

  private static createDefaultApiError(): ApiError {
    return {
      hasError: false,
      messages : [],
    }
  }
}

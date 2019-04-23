import ApiError from '@/domain/vo/api_error'

export default interface DialogError {
  title: ApiError,
  authorText: ApiError,
  authorSelect: ApiError,
  publisherText: ApiError,
  publisherSelect: ApiError,
  duplicated: ApiError,
}

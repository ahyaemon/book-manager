import ApiError from '@/domain/vo/ApiError'

export default interface DialogError {
  title: ApiError,
  authorText: ApiError,
  authorSelect: ApiError,
  publisherText: ApiError,
  publisherSelect: ApiError,
}

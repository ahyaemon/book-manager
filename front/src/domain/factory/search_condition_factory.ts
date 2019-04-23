import SearchCondition from '@/domain/vo/search_condition'

export default class SearchConditionFactory {

  public static default(): SearchCondition {
    return {
      title: '',
      authorId: null,
      publisherId: null,
    }
  }

}

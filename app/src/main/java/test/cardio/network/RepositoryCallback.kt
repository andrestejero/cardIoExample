package test.cardio.network


interface RepositoryCallback<T> {
    fun onSuccess(value: T?)

    fun onFailure()
}
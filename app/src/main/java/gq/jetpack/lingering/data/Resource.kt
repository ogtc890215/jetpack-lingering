package gq.jetpack.lingering.data

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Resource<out T>(val status: Status, val data: T?, val message: String?, val e: Exception?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(msg: String, data: T?, e: Exception? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg, e)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }
    }
}
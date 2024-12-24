import com.ms.auth.dto.response.ResultInfo

class ValidationException(errorInfo: ResultInfo) : RuntimeException() {
    private val resultInfo: ResultInfo = errorInfo
}
package tk.quietdev.level1.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import tk.quietdev.level1.common.Resource
import tk.quietdev.level1.data.UsersRepository
import tk.quietdev.level1.domain.models.UserModel

class FlowCurrentUserUseCase(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(): Flow<Resource<UserModel>> = flow {
        emit(Resource.Loading())
        val uId = usersRepository.fetchCurrentUserId()
        val flow = if (uId is Resource.Success) {
            usersRepository.flowUserById(uId.data!!)
        } else {
            flow {
                emit(Resource.Error<UserModel>(uId.message ?: "Failed to get userId"))
            }
        }
        emitAll(flow)
    }

}

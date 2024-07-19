import request from '@/utils/request.js'
// 封装所有和用户相关的接口函数

export const userInfoAPI = (token) => {
  return request({
    url: '/api/users/userIf',
    params: {
      token
    }
  })
}
export const getUsersAPI = ({pageNum,pageSize,trueName,email}) =>{
  return request({
    url:'/api/users/search',
    params:{
      pageNum,
      pageSize,
      trueName,
      email
    }
  })
}
export const updateUserAPI = (form) => {
  return request.put(`/api/users/${form.id}`, form)
}

export const deleteUserAPI = (id) => {
  return request.delete(`/api/users/${id}`)
}
export const updateUserPasswordAPI = ({ id, oldPassword, newPassword }) => {
  return request.post(`/api/users/password`, {
    id,
    oldPassword,
    newPassword
  })
}
export const applyToJoinAPI = ({userId,clubId}) => {
  return request.post('/api/users/join',{
    userId,
    clubId  
  })
  
}

export const starClubAPI=({userId,clubId})=>{
  return request.post('/api/users/star',{
    userId,
    clubId
  })
}
  

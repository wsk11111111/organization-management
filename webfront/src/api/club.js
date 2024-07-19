import request from '@/utils/request.js'

export const getClubsAPI = ({pageNum,pageSize,category,name}) =>{
  return request({
    url:'/api/clubs/search',
    params:{
      pageNum,
      pageSize,
      category,
      name
    }
  })
}
export const updateClubAPI = (form) => {
    return request.put(`/api/clubs/${form.id}`, form)
}
  
export const deleteClubAPI = (id) => {
  return request.delete(`/api/clubs/${id}`)
}

export const getStarClubAPI=({pageNum,pageSize,userId}) =>{
  return request({
    url:'api/clubs/starSearch',
    params:{
      pageNum,
      pageSize,
      userId
    }
  })
}

import request from '@/utils/request.js'
export const getClubsAPI = ({pageNum,pageSize,userId}) =>{
    return request({
      url:'/api/clubs/searchMine',
      params:{
        pageNum,
        pageSize,
        userId
      }
    })
  }

export const getClubAct = (clubId)=>{
    return request.get(`/api/clubs/getClubAct/${clubId}`)
}

export const setActAPI =(form)=>{
  return request.put(`/api/clubs/setAct/${form.id}`,form)
}
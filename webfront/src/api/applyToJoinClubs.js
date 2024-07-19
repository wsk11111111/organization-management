import request from '@/utils/request.js'



export const getApplyClubsAPI=({pageNum,pageSize,userId})=>{
    return request({
        url:'/api/atj/search',
        params:{
          pageNum,
          pageSize,
          userId

        }
      })
}

export const getClubComAPI=({pageNum,pageSize,userId})=>{
  return request({
    url:'/api/atj/searchClubCom',
    params:{
      pageNum,
      pageSize,
      userId

    }
  })
}
export const approveAPI = (id) => {
  return request.get(`/api/atj/${id}`)
}

export const disApproveAPI = (id) => {
  return request.delete(`/api/atj/${id}`)
}

export const applyToSetAPI =({category,name,userId})=>{

  return request({
    url:'/api/atj/applyToSet',
    params:{
      category,
      name,
      userId
    }
  })
}
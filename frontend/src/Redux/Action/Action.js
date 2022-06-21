import { ActionTypes } from "../constant"



export const setProduct=(products)=>
{
    return{
        type:ActionTypes.SET_PRODUCTS,
        payload:products
    }
}

export const selectedProduct=(product)=>
{
    return{
        type:ActionTypes.SELECTED_PRODUCTS,
        payload:product
    }
}

export const setClient=(client)=>
{
    return{
        type:ActionTypes.SELECTED_CLIENT,
        payload:client
    }    
}
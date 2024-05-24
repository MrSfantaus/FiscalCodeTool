export interface GenerateResponse {
  message: string,
  success: boolean,
  data: {
      fiscalCode: string,
      homocodesFiscalCode: string[]
  }
}
  
export interface ValidateResponse {
  message: string,
  success: boolean,
  data: {
      valid: boolean,
      homocodedFiscalCode: boolean,
      description: string
  }
}

export interface BelfioreDTO {
  province: string,
  municipality: string,
  belfioreCode: string,
  italianMunicipality: boolean
}

export interface BelfioreResponse {
  message: string,
  success: boolean,
  data: {
    belfioreCodes: BelfioreDTO[]
  }
}
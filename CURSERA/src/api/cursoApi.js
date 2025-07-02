import axiosClient from './axiosClient';

export const getCursos = async () =>
  (await axiosClient.get('/cursos')).data;

export const getCurso = async (id) =>
  (await axiosClient.get(`/cursos/${id}`)).data;

export const createCurso = async (curso) =>
  (await axiosClient.post('/cursos', curso)).data;

export const updateCurso = async (id, curso) =>
  (await axiosClient.put(`/cursos/${id}`, curso)).data;

export const deleteCurso = async (id) =>
  (await axiosClient.delete(`/cursos/${id}`)).data;

import axiosClient from './axiosClient';

export const getAlumnos = async () => (await axiosClient.get('/alumnos')).data;
export const getAlumno = async (id) =>
  (await axiosClient.get(`/alumnos/${id}`)).data;
export const createAlumno = async (alumno) => (await axiosClient.post('/alumnos', alumno)).data;
export const updateAlumno = async (id, alumno) => (await axiosClient.put(`/alumnos/${id}`, alumno)).data;
export const deleteAlumno = async (id) => (await axiosClient.delete(`/alumnos/${id}`)).data;
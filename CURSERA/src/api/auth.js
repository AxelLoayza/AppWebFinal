import axios from 'axios';



import axiosClient from './axiosClient';

export const login = async (credentials) => {
  const { data } = await axiosClient.post('/login', credentials);
  return data;
};

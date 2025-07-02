import { create } from 'zustand';

export const useAlumnoStore = create((set) => ({
  alumnos: [],
  setAlumnos: (alumnos) => set({ alumnos }),
}));
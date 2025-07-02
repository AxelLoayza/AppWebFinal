import { create } from 'zustand';

export const useCursoStore = create((set) => ({
  cursos: [],
  setCursos: (cursos) => set({ cursos }),
}));
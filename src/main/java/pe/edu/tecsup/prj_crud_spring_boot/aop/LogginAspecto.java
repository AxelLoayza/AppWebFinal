package pe.edu.tecsup.prj_crud_spring_boot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Alumno;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Auditoria;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Curso;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import pe.edu.tecsup.prj_crud_spring_boot.domain.persistence.AuditoriaDao;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component
@Aspect
public class LogginAspecto {
    private Long tx;

    @Autowired
    private AuditoriaDao auditoriaDao;

    @Around("execution(* pe.edu.tecsup.prj_crud_spring_boot.services.*ServiceImpl.*(..))")
    public Object LongAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        Long currTime = System.currentTimeMillis();
        tx = System.currentTimeMillis();
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = "tx[" + tx + "]- " + joinPoint.getSignature().getName();
        //Logger.info(metodo + "()");
        if (joinPoint.getArgs().length > 0)
            logger.info(metodo + "() INPUT:" + Arrays.toString(joinPoint.getArgs()));
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }
        logger.info(metodo + "() tiempo transcurrido" + (System.currentTimeMillis() - currTime) + "ms.");
        return result;
    }


    @AfterReturning("execution(* pe.edu.tecsup.prj_crud_spring_boot.services.*ServiceImpl.grabar*(..)) ||" +
            "execution(* pe.edu.tecsup.prj_crud_spring_boot.services.*ServiceImpl.eliminar*(..)) ||" +
            "execution(* pe.edu.tecsup.prj_crud_spring_boot.services.*ServiceImpl.editar*(..))")
    public void auditoria(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = joinPoint.getSignature().getName();

        Object[] parametros = joinPoint.getArgs();
        Integer id = null;
        String entidad = "";

        if (parametros.length > 0) {
            Object objeto = parametros[0];

            if (objeto instanceof Curso) {
                Curso curso = (Curso) objeto;
                id = curso.getId();
                entidad = "curso";
            } else if (objeto instanceof Alumno) {
                Alumno alumno = (Alumno) objeto;
                id = alumno.getId();
                entidad = "alumno";
            }
        }

        if (id != null && !entidad.isEmpty()) {
            auditoriaDao.save(new Auditoria(entidad, id, Calendar.getInstance().getTime(), "usuario", metodo));
        }
    }

}
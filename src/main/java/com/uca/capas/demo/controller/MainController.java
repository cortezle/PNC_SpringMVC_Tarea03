package com.uca.capas.demo.controller;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/resultado")
	public ModelAndView user1(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fechaN = request.getParameter("fechaN");
		String lugar = request.getParameter("lugar");
		String instituto = request.getParameter("instituto");
		String tFijo = request.getParameter("tel");
		String tMovil = request.getParameter("cel");
		
		List<String> listaErrores = new ArrayList<>();
		
		
		validarNombre( nombre, listaErrores, mav);
		validarApellido(apellido,listaErrores,mav);
		validarFecha(fechaN, listaErrores, mav);
		validarLugarDeNacimiento(lugar,listaErrores,mav);
		validarInstituto(instituto,listaErrores,mav);
		validarTelefonoFijo(tFijo,listaErrores,mav);
		validarTelefonoMovil(tMovil, listaErrores, mav);
		
		if(!listaErrores.isEmpty()) {
			mav.addObject("errores", listaErrores);
			mav.addObject("apellido", apellido);
			mav.setViewName("registro_error.html");
		}else {
			mav.setViewName("registrado.html");
		}
		return mav;
	}
	
	public Boolean validarMayorCero(String nom) {
		if(nom.length()>0) {
			return true;
		}
		else {
			return false;
		}
	}
	public Boolean validarMayor25(String nom) {
		if(nom.length()>25) {
			return true;
		}
		else {
			return false;
		}
	}
	public Boolean validarMayor100(String nom) {
		if(nom.length()>100) {
			return true;
		}
		else {
			return false;
		}
	}
	public Boolean validarIgual8(String nom) {
		if(nom.length()==8) {
			return true;
		}
		else {
			return false;
		}
	}
	public void validarNombre(String nombre, List<String> listaErrores, ModelAndView mav) {
		if(validarMayorCero(nombre)) {
			if(validarMayor25(nombre)) {
				listaErrores.add("El nombre no puede ser mayor a 25 caracteres");
			}
			else {
				mav.addObject("nombre", nombre);
			}
			
		}else {
			listaErrores.add("El nombre no puede estar vacio");
		}
		
	}
	public void validarApellido(String apellido, List<String> listaErrores, ModelAndView mav) {
		if(validarMayorCero(apellido)) {
			if(validarMayor25(apellido)) {
				listaErrores.add("El apellido no puede ser mayor a 25 caracteres");
			}
			else {
				mav.addObject("apellido", apellido);
			}
			
		}else {
			listaErrores.add("El apellido no puede estar vacio");
		}
	}
	
	public void validarLugarDeNacimiento(String lugar, List<String> listaErrores, ModelAndView mav) {
		if(validarMayorCero(lugar)) {
			if(validarMayor25(lugar)) {
				listaErrores.add("El lugar de nacimiento no puede ser mayor a 25 caracteres");
			}
			else {
				mav.addObject("lugar", lugar);
			}
			
		}else {
			listaErrores.add("El lugar de nacimiento no puede estar vacio");
		}
	}
	
	public void validarInstituto(String instituto, List<String> listaErrores, ModelAndView mav) {
		if(validarMayorCero(instituto)) {
			if(validarMayor100(instituto)) {
				listaErrores.add("El lugar de instituto no puede ser mayor a 100 caracteres");
			}
			else {
				mav.addObject("instituto", instituto);
			}
			
		}else {
			listaErrores.add("El lugar de instituto no puede estar vacio");
		}
	}
	
	public void validarTelefonoFijo(String tel, List<String> listaErrores, ModelAndView mav) {
		if(validarIgual8(tel)) {
			mav.addObject("tel", tel);
		}else {
			listaErrores.add("El telefono fijo tiene que ser de 8 numeros");
		}
		
	}
	
	public void validarTelefonoMovil(String cel, List<String> listaErrores, ModelAndView mav) {
		if(validarIgual8(cel)) {
			mav.addObject("cel", cel);
		}else {
			listaErrores.add("El telefono movil tiene que ser de 8 numeros");
		}
		
	}
	public void validarFecha(String fechaN, List<String> listaErrores, ModelAndView mav) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(fechaN=="") {
			fechaN="0001-01-01";
		}
		LocalDate fechaNac = LocalDate.parse(fechaN.toString(), formatter);
		LocalDate fechaMin = LocalDate.parse("2003-01-01", formatter);

		if(fechaNac.isAfter(fechaMin)){
		   //fecha buena
			mav.addObject("fechaN", fechaN);
			
		}else{
		   //fecha error
			listaErrores.add("Fecha tiene que ser mayor a 2003-01-01");
		}
	}
	

	

}
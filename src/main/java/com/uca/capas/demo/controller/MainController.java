package com.uca.capas.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
		String lugar = request.getParameter("lugar");
		String instituto = request.getParameter("instituto");
		String tFijo = request.getParameter("tel");
		String tMovil = request.getParameter("cel");
		//ingresar la fecha con string con formato AAAA/MM/DD y hacerle split y trabajarlo con lista
		String dia = request.getParameter("fechaN").toString();
		
		
		List<String> listaErrores = new ArrayList<>();
		
		validarNombre( nombre, listaErrores, mav);
		validarApellido(apellido,listaErrores,mav);
		validarLugarDeNacimiento(lugar,listaErrores,mav);
		validarInstituto(instituto,listaErrores,mav);
		validarTelefonoFijo(tFijo,listaErrores,mav);
		validarTelefonoMovil(tMovil, listaErrores, mav);
		
		if(!listaErrores.isEmpty()) {
			mav.addObject("errores", listaErrores);
			mav.addObject("apellido", apellido);
			mav.setViewName("registro_error.html");
		}else {
			mav.addObject("apellido", "exito");
			mav.setViewName("registrado.html");
		}
		mav.addObject("fechaN", dia);
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
				listaErrores.add("El nombre fue mayor a 25");
			}
			else {
				mav.addObject("nombre", nombre);
			}
			
		}else {
			listaErrores.add("El nombre esta vacio");
		}
		
	}
	public void validarApellido(String apellido, List<String> listaErrores, ModelAndView mav) {
		if(validarMayorCero(apellido)) {
			if(validarMayor25(apellido)) {
				listaErrores.add("El apellido fue mayor a 25");
			}
			else {
				mav.addObject("apellido", apellido);
			}
			
		}else {
			listaErrores.add("El apellido esta vacio");
		}
	}
	
	public void validarLugarDeNacimiento(String lugar, List<String> listaErrores, ModelAndView mav) {
		if(validarMayorCero(lugar)) {
			if(validarMayor25(lugar)) {
				listaErrores.add("El lugar de nacimiento fue mayor a 25");
			}
			else {
				mav.addObject("lugar", lugar);
			}
			
		}else {
			listaErrores.add("El lugar de nacimiento esta vacio");
		}
	}
	
	public void validarInstituto(String instituto, List<String> listaErrores, ModelAndView mav) {
		if(validarMayorCero(instituto)) {
			if(validarMayor100(instituto)) {
				listaErrores.add("El lugar de instituto fue mayor a 25");
			}
			else {
				mav.addObject("instituto", instituto);
			}
			
		}else {
			listaErrores.add("El lugar de instituto esta vacio");
		}
	}
	
	public void validarTelefonoFijo(String tel, List<String> listaErrores, ModelAndView mav) {
		if(validarIgual8(tel)) {
			mav.addObject("tel", tel);
		}else {
			listaErrores.add("El telefono tiene que ser de 8 numeros");
		}
		
	}
	
	public void validarTelefonoMovil(String cel, List<String> listaErrores, ModelAndView mav) {
		if(validarIgual8(cel)) {
			mav.addObject("cel", cel);
		}else {
			listaErrores.add("El telefono Movil tiene que ser de 8 numeros");
		}
		
	}
	

	

}
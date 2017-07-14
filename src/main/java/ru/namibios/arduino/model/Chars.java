package ru.namibios.arduino.model;

import java.util.ArrayList;
import java.util.List;

public enum Chars {
	
	W(new int[][]{  {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, },
					{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, },
					{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
					{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, },
					{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }, 
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
		
	 new int[][]{   {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, },
					{0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, },
					{0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, },
					{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }}),
	
	A(new int[][]{  {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }},
		
		new int[][]{{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }}),
	
	S(new int [][]{ {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, },
					{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
					{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
					{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, },
					{0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, },
					{0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},

	  new int[][]{  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, },
					{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
					{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
					{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, },
					{0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, },
					{0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }}),
	
	D(new int[][]{  {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }},
			
	  new int[][]{  {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, },
					{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, },
					{1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }}),
	
	SPACE(
		new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, },
			{0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
			{0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, },
			{0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, },
			{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, },
			{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, },
			{0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }}
		
			);
	
	private final List<int[][]> templates;
	
	public List<int[][]> getTemplates() {
		return templates;
	}
	
	private Chars(int[][]... mas){
		this.templates = new ArrayList<int[][]>();
		for (int[][] template : mas) {
			templates.add(template);
		}
	}
	
}
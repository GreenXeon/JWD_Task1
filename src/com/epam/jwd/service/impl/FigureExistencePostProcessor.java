package com.epam.jwd.service.impl;

import com.epam.jwd.exception.FigureException;
import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.model.Figure;
import com.epam.jwd.model.Line;
import com.epam.jwd.model.Point;
import com.epam.jwd.model.Triangle;
import com.epam.jwd.service.FigurePostProcessor;

public final class FigureExistencePostProcessor implements FigurePostProcessor {
    private static FigureExistencePostProcessor instance;
    private FigureExistencePostProcessor(){
    }
    public static FigureExistencePostProcessor getInstance(){
        if (instance == null){
            instance = new FigureExistencePostProcessor();
        }
        return instance;
    }

    @Override
    public Figure process(Figure figure) throws FigureException {
        if (figure instanceof Line){
            return figure;
        }
        int equalX = 1;
        int equalY = 1;
        Point[] points = figure.getPoints();
        for(int i = 0; i < figure.numOfPoints()-1; i++){
            for (int j = i+1; j < figure.numOfPoints(); j++){
                if (points[i].getX() == points[j].getX()){
                    equalX++;
                }
                if (points[i].getY() == points[j].getY()){
                    equalY++;
                }
            }
        }
        if (equalX == figure.numOfPoints() || equalY == figure.numOfPoints() || !figure.validationCheck()){ //todo:multiangles
            throw new FigureNotExistException("Figure can't exist");
        }
        return figure;
    }
}

CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE public.functions(
    function_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    xFrom NUMERIC(100, 10) ,
    xTo NUMERIC(100, 10) ,
    count INTEGER
);

CREATE TABLE public.function_points (
    point_id SERIAL PRIMARY KEY,
    function_id INTEGER NOT NULL,
    x NUMERIC(100, 10) ,
    y NUMERIC(100, 10) ,
    CONSTRAINT fk_function
        FOREIGN KEY (function_id)
        REFERENCES public.functions(function_id)
        ON DELETE CASCADE
);

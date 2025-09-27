INSERT INTO professors(name, registration, email, hashed_password, role)
VALUES
('Luciano', '11111111', 'luciano@fatec.sp.gov.br', 'senha123', 'COURSE_COORDINATOR'),
('Cristina', '22222222', 'cristina@fatec.sp.gov.br', 'senha123', 'TG_COORDINATOR');

/*---------------------------------*/

INSERT INTO courses(name, shift, tg_coordinator_id, course_coordinator_id) 
VALUES('ADS - Análise e Desenvolvimento de Sistemas', 'NIGHT', 2, 1);
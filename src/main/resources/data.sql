-- USERS
INSERT INTO users (id, name, email, password, city, skills, foto, field_of_interest)
VALUES
  (1, 'Maria Silva', 'maria@follon.com', '$2b$10$3vCrvPFh15e4rsnk9HKYZezgiziBZKNSxseSaoudocós45N2Tqfy', 'São Paulo', 'Java, Spring Boot', NULL, '{"Backend", "Java"}'),
  (2, 'João Pereira', 'joao@follon.com', '$2b$10$D2MVd3YLC1qSA0eDPczBr.Y9M86lHQKuVDKahY6QrfVdDKSo1lyUi', 'Rio de Janeiro', 'Product, UX', NULL, '{"Design", "Product"}'),
  (3, 'Ana Costa', 'ana@follon.com', '$2b$10$EmUay/odXSYqGPH560puxOBK8LDVUUhOuKKna/sCMCYVgrFkbO1YC', 'Curitiba', 'AI, Data Science', NULL, '{"AI", "Data"}');

-- HACKATHON
INSERT INTO hackathon (id, title, description, location, format, start_date, end_date, registration_start, registration_end, prize)
VALUES
  (1, 'Hackathon de IA para Saúde', 'Desenvolva soluções inovadoras para saúde usando IA.', 'São Paulo', 'Presencial', '2026-11-10', '2026-11-12', '2026-10-01', '2026-10-30', 15000.00),
  (2, 'Desafio de Sustentabilidade', 'Crie projetos focados em impacto ambiental e urbano.', 'Rio', 'Híbrido', '2026-12-05', '2026-12-07', '2026-10-15', '2026-11-15', 12000.00);

-- BADGES
INSERT INTO badges (id, hackathon_id, foto)
VALUES
  (1, 1, NULL),
  (2, 2, NULL);

-- PARTICIPATION
INSERT INTO participation (user_id, hackathon_id)
VALUES
  (1, 1),
  (2, 1),
  (1, 2),
  (3, 2);

-- TEAM
INSERT INTO team (id, name, description, hackathon_id, badge_id)
VALUES
  (1, 'Neural Care', 'Equipe focada em IA para diagnósticos assistidos.', 1, 1),
  (2, 'Eco Flow', 'Time de soluções para mobilidade e energia limpa.', 2, 2);

-- TEAM_REQUEST
INSERT INTO team_request (team_id, user_id, status)
VALUES
  (1, 2, 'PENDING'),
  (2, 3, 'APPROVED');

-- SUBMISSION
INSERT INTO submission (id, team_id, project_name, description, github, demo)
VALUES
  (1, 1, 'CareVision', 'Plataforma de triagem inteligente para serviços de saúde.', 'https://github.com/follon/carevision', 'https://demo.example/carevision'),
  (2, 2, 'EcoRoute', 'Sistema para otimização de rotas com impacto ambiental.', 'https://github.com/follon/ecoroute', 'https://demo.example/ecoroute');

-- EVALUATION
INSERT INTO evaluation (id, submission_id, judge_id, innovation, execution, design, impact, feedback)
VALUES
  (1, 1, 3, 9, 8, 9, 10, 'Excelente solução com alto impacto social.'),
  (2, 2, 1, 8, 8, 7, 9, 'Projeto bem executado e de grande relevância sustentável.');

-- ATUALIZAR SEQUÊNCIAS DE ID DO POSTGRES
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('hackathon_id_seq', (SELECT MAX(id) FROM hackathon));
SELECT setval('badges_id_seq', (SELECT MAX(id) FROM badges));
SELECT setval('team_id_seq', (SELECT MAX(id) FROM team));
SELECT setval('submission_id_seq', (SELECT MAX(id) FROM submission));
SELECT setval('evaluation_id_seq', (SELECT MAX(id) FROM evaluation));
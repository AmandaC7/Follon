DELETE FROM "EVALUATION";
DELETE FROM "SUBMISSION";
DELETE FROM "TEAM_REQUEST";
DELETE FROM "TEAM";
DELETE FROM "PARTICIPATION";
DELETE FROM "HACKATHON";
DELETE FROM "USERS";

INSERT INTO "USERS" (id, name, email, password, city, skills, foto, field_of_interest)
VALUES
  (1, 'Maria Silva', 'maria@follon.com', '$2b$10$3vCrvPFh15e4rsnk9HKYZezgjziBZKNSxseSaoudoc6s45N2Ztgfy', 'São Paulo', 'Java,Spring Boot,PostgreSQL', '\x010203', ARRAY['Java', 'Spring Boot', 'PostgreSQL']),
  (2, 'João Pereira', 'joao@follon.com', '$2b$10$D2MVd3YLClqSA0eDPczBr.Y9M86lHQKuVDKahY6QrfVdDKSo1lyUi', 'Rio de Janeiro', 'Product,UX,Design', '\x040506', ARRAY['Product', 'UX']),
  (3, 'Ana Costa', 'ana@follon.com', '$2b$10$EmUay/odXSYqGPH560puxOBK8LDVUUhOuKKna/sCMCYVgrFkbOlYC', 'Curitiba', 'AI,Data Science', '\x070809', ARRAY['AI', 'Data Science']);

INSERT INTO "HACKATHON" (id, title, description, location, format, start_date, end_date, registration_start, registration_end, prize)
VALUES
  (1, 'Hackathon de IA para Saúde', 'Desenvolva soluções inovadoras para saúde usando IA.', 'São Paulo', 'Presencial', '2026-11-10', '2026-11-12', '2026-10-01', '2026-10-30', 15000.00),
  (2, 'Desafio de Sustentabilidade', 'Crie projetos focados em impacto ambiental e urbano.', 'Rio', 'Híbrido', '2026-12-05', '2026-12-07', '2026-10-15', '2026-11-15', 12000.00);

INSERT INTO "PARTICIPATION" (user_id, hackathon_id)
VALUES
  (1, 1),
  (2, 1),
  (1, 2),
  (3, 2);

INSERT INTO "TEAM" (id, name, description, hackathon_id)
VALUES
  (1, 'Neural Care', 'Equipe focada em IA para diagnósticos assistidos.', 1),
  (2, 'Eco Flow', 'Time de soluções para mobilidade e energia limpa.', 2);

INSERT INTO "TEAM_REQUEST" (team_id, user_id, status)
VALUES
  (1, 2, 'PENDING'),
  (2, 3, 'APPROVED');

INSERT INTO "SUBMISSION" (id, team_id, project_name, description, github, demo)
VALUES
  (1, 1, 'CareVision', 'Plataforma de triagem inteligente para serviços de saúde.', 'https://github.com/follon/carevision', 'https://demo.example/carevision'),
  (2, 2, 'EcoRoute', 'Sistema para otimização de rotas com impacto ambiental.', 'https://github.com/follon/ecoroute', 'https://demo.example/ecoroute');

INSERT INTO "EVALUATION" (id, submission_id, judge_id, innovation, execution, design, impact, feedback)
VALUES
  (1, 1, 3, 9, 8, 9, 10, 'Excelente solução com alto impacto social.'),
  (2, 2, 1, 8, 8, 7, 9, 'Projeto bem executado e de grande relevância sustentável.');

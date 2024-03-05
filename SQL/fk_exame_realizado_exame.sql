ALTER TABLE avaliacao.exame_realizado
ADD CONSTRAINT fk_exame_realizado_exame
FOREIGN KEY (cd_exame) REFERENCES avaliacao.exame(cd_exame);
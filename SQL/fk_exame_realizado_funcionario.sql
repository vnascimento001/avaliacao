ALTER TABLE avaliacao.exame_realizado
ADD CONSTRAINT fk_exame_realizado_funcionario
FOREIGN KEY (cd_funcionario) REFERENCES avaliacao.funcionario(cd_funcionario)
ON DELETE CASCADE;
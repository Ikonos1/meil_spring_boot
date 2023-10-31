use meil;

SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO project(projid,createdate, editdate, isdeleted, isoandm, projname, projvalue, remarks, createuserid, dupprojid, edituserid, hsecoordid, hsemgrid, pmrepauthid,
                    projcoordid, projhodid, sectorcode, statecode, status)
VALUES (1, now(), now(), 0, 0, 'Meil', 0, 'Meil', 1, 0, 1, 1, 1, 1, 1, 1, 'M', 'A', '0');
SET FOREIGN_KEY_CHECKS = 1;

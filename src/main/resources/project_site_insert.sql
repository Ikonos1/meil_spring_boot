use meil;

SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO projectsite(projsiteid, courierpmobile, createdate, editdate, isdeleted, latitude, longitude, sitename, addressid, courierpcode, createuserid, edituserid, projcoordid,
                        projid, sitemanagerid)
VALUES (1, '1234567890', now(), now(), 0, 0, 0, 'site1', 1, '123456', 1, 1, 1, 1, 1);
SET FOREIGN_KEY_CHECKS = 1;

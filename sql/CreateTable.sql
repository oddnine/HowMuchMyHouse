create table hmmh
(
 ID bigint generated by default as identity,
 AMOUNT integer,
 BUILD_YEAR integer,
 DEAL_YEAR integer,
 DEAL_MONTH integer,
 DEAL_DAY integer,
 SIGUNGU_CODE integer,
 EUPMYUNDONG_CODE integer,
 SIGUNGU varchar(20),
 DONG varchar(20),
 APT_NAME varchar(20),
 DEDICATED_AREA double,
 FLOOR integer,
 primary key (id)
);
create table delivery.oauth_client_details (
  client_id varchar(255),
  resource_ids varchar(256),
  client_secret varchar(256),
  scope varchar(256),
  authorized_grant_types varchar(256),
  web_server_redirect_uri varchar(256),
  authorities varchar(256),
  access_token_validity integer,
  refresh_token_validity integer,
  additional_information varchar(4096),
  autoapprove varchar(256),
  primary key (client_id)
) engine=innodb default charset=utf8;

insert into delivery.oauth_client_details values ('delivery', null, '$2a$12$AREDHuiWLN9Lx22WsBjbHuAyye2nrP8bnl9i6m0.tEOb0Ae.8NzBG',
'READ,WRITE', 'password', null, null, 0, 0, null, null);
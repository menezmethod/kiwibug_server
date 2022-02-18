INSERT INTO public.employee (employee_id, employee_name, email, username, password, created_on, created_by, modified_on, modified_by, assigned_projects) VALUES (1, 'Luis Gimenez', 'menez@menez.com', 'menez', '$2a$10$1if5PYM8Q9AoezYvf6VDKeBNYo7Eo0/qv0818ooHAotXilgP7e9ay', '2021-12-16 13:06:49.728706', null, '2021-12-16 13:06:49.729679', null, null);
INSERT INTO public.employee (employee_id, employee_name, email, username, password, created_on, created_by, modified_on, modified_by, assigned_projects) VALUES (2, 'Kiwi Husky', 'kiwi@menez.com', 'kiwi', '$2a$10$7R7kRBAaLzCUCaE4hVzdIuGIX6KOKkAXwe2xveW9.Az6fvGsiySsK', '2021-12-16 13:11:27.645556', null, '2021-12-16 13:11:27.646555', null, null);
INSERT INTO public.employee (employee_id, employee_name, email, username, password, created_on, created_by, modified_on, modified_by, assigned_projects) VALUES (3, 'Thomas Baker', 'tbaker@menez.com', 'tbaker', '$2a$10$xDDhdPEb3IT5TCVHj0X8IOx8fsZEfYia8jz50b.yLUGPGvsbXsSs.', '2021-12-16 13:12:42.630723', null, '2021-12-16 13:12:42.630723', null, null);
INSERT INTO public.employee (employee_id, employee_name, email, username, password, created_on, created_by, modified_on, modified_by, assigned_projects) VALUES (4, 'Bob Johnson', 'bjohnson@menez.com', 'bjohnson', '$2a$10$FldzE/.XqSnQKD8CYus88ew9uw1cEL6qgdVROsP.n4qQX8HeFeun2', '2021-12-16 13:13:33.600880', null, '2021-12-16 13:13:33.600880', null, null);

INSERT INTO public.employee_roles (employee_id, role_id) VALUES (1, 1);
INSERT INTO public.employee_roles (employee_id, role_id) VALUES (1, 4);
INSERT INTO public.employee_roles (employee_id, role_id) VALUES (2, 1);
INSERT INTO public.employee_roles (employee_id, role_id) VALUES (2, 3);
INSERT INTO public.employee_roles (employee_id, role_id) VALUES (3, 1);
INSERT INTO public.employee_roles (employee_id, role_id) VALUES (3, 2);
INSERT INTO public.employee_roles (employee_id, role_id) VALUES (4, 1);
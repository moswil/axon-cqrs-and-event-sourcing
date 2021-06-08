package com.github.moswil.learn.user.query.api.handlers;

import com.github.moswil.learn.user.query.api.dto.UserLookupResponse;
import com.github.moswil.learn.user.query.api.queries.FindAllUsersQuery;
import com.github.moswil.learn.user.query.api.queries.FindUserByIdQuery;
import com.github.moswil.learn.user.query.api.queries.SearchUsersQuery;


public interface UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}

#!/usr/bin/env bash

# ====================================================================================================================
# Vars
# ====================================================================================================================

PORT=8088;
REALM=placeholder;

#USERNAME="james@placeholder.com";
#USERNAME="jennifer@placeholder.com";
#USERNAME="john@placeholder.com";
#USERNAME="mary@placeholder.com";
#USERNAME="patricia@placeholder.com";
USERNAME="robert@placeholder.com";

PASSWORD="password";

#CLIENT="client-one"
CLIENT="client-two"

AUTH_CHECK_TYPE="AUTHENTICATED" # We only need a valid user here
#AUTH_CHECK_TYPE="AUTHENTICATED_WITH_ROLE" # Only robert has the required role for this
#AUTH_CHECK_TYPE="AUTHENTICATED_WITH_CLIENT" # Only client-two is allowed

# ====================================================================================================================
# Get user token
# ====================================================================================================================

ACCESS_TOKEN=$(curl \
  -d "client_id=$CLIENT" \
  -d "username=$USERNAME" \
  -d "password=$PASSWORD" \
  -d "grant_type=password" \
  -X POST "http://localhost:$PORT/auth/realms/$REALM/protocol/openid-connect/token" | jq -r .access_token);

echo "$ACCESS_TOKEN";

# ====================================================================================================================
# Get a specific user from our custom resource
# ====================================================================================================================

USER="james"
#USER="jennifer"
#USER="john"
#USER="mary"
#USER="patricia"
#USER="robert"

curl "http://localhost:$PORT/auth/realms/$REALM/custom/users/$USER?auth_check_type=$AUTH_CHECK_TYPE" \
  --header "Accept: application/json" \
  --header "Authorization: Bearer $ACCESS_TOKEN" | python -m json.tool;

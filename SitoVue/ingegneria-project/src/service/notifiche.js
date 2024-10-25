import {mantieniAggiornamenti} from '../scripts/websocket/websocket.js';


function apriWebSocketNotifichePersonali(username, callback) {
    return mantieniAggiornamenti('notifichePersonali/' + username, callback,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb3Jvc2luaS5yYWd1c2FAZ21haWwuY29tIiwicGVybWVzc2kiOlsiY3JlYV9hc3RhX2luZ2xlc2UiLCJjcmVhX2FzdGFfc2lsZW56aW9zYSIsImZhaV9vZmZlcnRhX2FzdGFfaW52ZXJzYSJdLCJleHAiOjE3Mjk2MTE0NjAsImlhdCI6MTcyOTUyNTA2MH0.hV_DCDMUbNXU2PtTkIHtYarspwoBz3HP0QBeowXP1r0");
 
}

export { apriWebSocketNotifichePersonali };
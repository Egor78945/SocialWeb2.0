package org.example.j2ee.messageservice.model.dto.message;

import lombok.Data;

import java.util.List;

@Data
public class MessageList {
    private List<String> messageAddress;
    private List<String> message;
    private String currentUserId;

    public MessageList(List<String> messageAddress, List<String> message, String currentUserId) {
        this.messageAddress = messageAddress;
        this.message = message;
        this.currentUserId = currentUserId;
    }

    public MessageList() {
    }

    @Override
    public String toString() {
        if(messageAddress.isEmpty() || message.isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < messageAddress.size(); i++){
            String[] messageAddressArray = messageAddress.get(i).split("/");
            if(messageAddressArray[0].equals(currentUserId)){
                sb.append("Me: ");
                sb.append(message.get(i));
            } else {
                sb.append(messageAddressArray[1]);
                sb.append(": ");
                sb.append(message.get(i));
            }
            if(i < messageAddress.size()-1){
                sb.append("\n");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}

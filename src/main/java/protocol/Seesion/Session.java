package protocol.Seesion;

import lombok.Data;

import java.util.List;

/**
 * 描述:
 * 用户表示
 *
 * @author caorui1
 * @create 2018-10-29 16:14
 */
@Data
public class Session {
    private String id;
    private String name;
    //存储下来的群消息
    private List<String> groupIds;
    //联系人电话簿
    private List<String> phoneBook;

    @Override
    public String toString() {
        return id + ":" + name;
    }

    public Session(){

    }

    public Session(String id,String name){
        this.id = id;
        this.name = name;
    }

    public void addGroupId(String groupId){
        this.groupIds.add(groupId);
    }

    public void deleteGroupId(String groupId){
        this.groupIds.remove(groupId);
    }
    public void addphoneBook(String pb){
        this.phoneBook.add(pb);
    }

}

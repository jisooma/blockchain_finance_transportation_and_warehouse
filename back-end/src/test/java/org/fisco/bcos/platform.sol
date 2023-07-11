pragma solidity ^0.4.24;
pragma experimental ABIEncoderV2;
import "./Table.sol";
contract platform
{
    event CreateResult(int count);
    event InsertResult(int count);
    event UpdateResult(int count);
    event RemoveResult(int count);

    function create_company_table() public returns (int)
    {// 创建企业表
        TableFactory tf = TableFactory(0x1001); //The fixed address is 0x1001 for TableFactory
        int count = tf.createTable("company", "index", "name, address, status, level, credit");
        // index索引主键；name企业名字；address账户地址；status运营状态（0: 正常运营；1: 破产）；
        // level等级（1: 金融机构；2: 普通企业）；credit信用值
        emit CreateResult(count);
        return count;
    }

    function openCompanyTable() private returns(Table)
    {// 打开企业表
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("company");
        return table;
    }

    // register for companies
    function register(string _name, int _level, string _credit, address sender_address) public returns (int)
    {// 注册企业/金融机构
        if (is_registered(_name)==1)// 不允许同名企业存在并正常运营
            return -1;
        Table table = openCompanyTable();
        Entry entry_to_insert = table.newEntry();
        entry_to_insert.set("name", _name);
        entry_to_insert.set("address", sender_address);
        entry_to_insert.set("status", int(0));
        entry_to_insert.set("level", _level);
        entry_to_insert.set("credit", _credit);
        int count = table.insert("com", entry_to_insert);
        emit InsertResult(count);
        return count;
    }

    function select_company() public constant returns (string[], address[], int[], int[], string[])
    {// 查看所有运营的企业/金融机构
        Table table = openCompanyTable();
        Entries entries = table.select("com", table.newCondition());
        string[] memory name_list = new string[](uint256(entries.size()));
        address[] memory addr_list = new address[](uint256(entries.size()));
        int[] memory status_list = new int[](uint256(entries.size()));
        int[] memory level_list = new int[](uint256(entries.size()));
        string[] memory credit_list = new string[](uint256(entries.size()));
        for(int i=0; i<entries.size(); ++i)
        {
            Entry entry = entries.get(i);
            name_list[uint256(i)] = entry.getString("name");
            addr_list[uint256(i)] = entry.getAddress("address");
            status_list[uint256(i)] = entry.getInt("status");
            level_list[uint256(i)] = entry.getInt("level");
            credit_list[uint256(i)] = entry.getString("credit");
        }
        return (name_list, addr_list, status_list, level_list, credit_list);
    }

    function select_company_byName(string name) public constant returns (address[], int[], int[], string[])
    {// 查看符合名字的企业信息
        Table table = openCompanyTable();
        Condition condition = table.newCondition();
        condition.EQ("index", "com");
        condition.EQ("name", name);

        Entries entries = table.select("com", condition);
        address[] memory addr_list = new address[](uint256(entries.size()));
        int[] memory status_list = new int[](uint256(entries.size()));
        int[] memory level_list = new int[](uint256(entries.size()));
        string[] memory credit_list = new string[](uint256(entries.size()));
        for(int i=0; i<entries.size(); ++i)
        {
            Entry entry = entries.get(i);
            addr_list[uint256(i)] = entry.getAddress("address");
            status_list[uint256(i)] = entry.getInt("status");
            level_list[uint256(i)] = entry.getInt("level");
            credit_list[uint256(i)] = entry.getString("credit");
        }
        return (addr_list, status_list, level_list, credit_list);
    }

    function is_registered(string name) public constant returns (int)
    {// 判断企业/金融机构是否已被注册并正常运营
        Table table = openCompanyTable();
        Condition condition = table.newCondition();
        condition.EQ("index", "com");
        condition.EQ("name", name);
        condition.EQ("status", 0);

        Entries entries = table.select("com", condition);
        if (0 == uint256(entries.size()))
            return -1;
        else
            return 1;
    }

    function verify_account(string name, address Saddress) private constant returns(int)
    {// 验证登陆账户
        Table table = openCompanyTable();
        Condition condition = table.newCondition();
        condition.EQ("index", "com");
        condition.EQ("name", name);
        Entries entries = table.select("com", condition);
        if (entries.size()<=0)// 不存在该企业
            return -1;
        Entry entry=entries.get(0);
        if (entry.getAddress("address")==Saddress)// 账户地址相同
            return 1;
        return -1;
    }

    function update_company(string _name, address _addr, int _status, int _level, string _credit) public returns(int)
    {// 更新企业表
        Table table = openCompanyTable();
        Entry entry = table.newEntry();
        entry.set("index", "com");
        entry.set("name", _name);
        entry.set("address", _addr);
        entry.set("status", _status);
        entry.set("level", _level);
        entry.set("credit", _credit);
        Condition condition = table.newCondition();
        condition.EQ("name", _name);
        int count = table.update("com", entry, condition);
        emit UpdateResult(count);
        return count;
    }

    function create_receipt_table() public returns(int)
    {// 创建交易数据表
        TableFactory tf = TableFactory(0x1001); //The fixed address is 0x1001 for TableFactory
        int count = tf.createTable("receipt", "index", "id, from, to, cur_bill, orig_bill, status, due_date");
        // index索引主键；id交易账单编号；from应付方；to应得方；cur_bill剩余未付款项；orig_bill总款项
        // status账单状态（-1: 审批未通过；0：等待审批；1：生效；2：失效）；due_date应还日期
        emit CreateResult(count);
        return count;
    }

    function openReceiptTable() private returns(Table)
    {// 打开交易数据表
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("receipt");
        return table;
    }

    function getToExtractInfo(Entries entries) private constant returns(int[], string[], string[], string[], int[], string[])
    {// 获取与from有关的交易信息
        int[] memory id_list = new int[](uint256(entries.size()));
        string[] memory to_list = new string[](uint256(entries.size()));
        string[] memory cur_bill_list = new string[](uint256(entries.size()));
        string[] memory orig_bill_list = new string[](uint256(entries.size()));
        int[] memory status_list = new int[](uint256(entries.size()));
        string[] memory due_date_list = new string[](uint256(entries.size()));
        for(int i = 0; i < entries.size(); ++i)
        {
            Entry entry = entries.get(i);
            id_list[uint256(i)] = entry.getInt("id");
            to_list[uint256(i)] = entry.getString("to");
            cur_bill_list[uint256(i)] = entry.getString("cur_bill");
            orig_bill_list[uint256(i)] = entry.getString("orig_bill");
            status_list[uint256(i)] = entry.getInt("status");
            due_date_list[uint256(i)] = entry.getString("due_date");
        }
        return (id_list, to_list, cur_bill_list, orig_bill_list, status_list, due_date_list);
    }

    function getFromExtractInfo(Entries entries) private constant returns(int[], string[], string[], string[], int[], string[])
    {// 获取与to有关的交易信息
        int[] memory id_list = new int[](uint256(entries.size()));
        string[] memory from_list = new string[](uint256(entries.size()));
        string[] memory cur_bill_list = new string[](uint256(entries.size()));
        string[] memory orig_bill_list = new string[](uint256(entries.size()));
        int[] memory status_list = new int[](uint256(entries.size()));
        string[] memory due_date_list = new string[](uint256(entries.size()));
        for(int i = 0; i < entries.size(); ++i)
        {
            Entry entry = entries.get(i);
            id_list[uint256(i)] = entry.getInt("id");
            from_list[uint256(i)] = entry.getString("from");
            cur_bill_list[uint256(i)] = entry.getString("cur_bill");
            orig_bill_list[uint256(i)] = entry.getString("orig_bill");
            status_list[uint256(i)] = entry.getInt("status");
            due_date_list[uint256(i)] = entry.getString("due_date");
        }
        return (id_list, from_list, cur_bill_list, orig_bill_list, status_list, due_date_list);
    }

    function select_Receipt_byCompany(string company, int mode) public constant returns(int[], string[], string[], string[], int[], string[])
    {// 查看与company有关的交易信息（mode=0：company为from；mode=1：company为to）
        Table table = openReceiptTable();
        Condition condition = table.newCondition();
        condition.EQ("index", "rec");
        Entries entries;
        if(mode == 0)
        {
            condition.EQ("from", company);
            entries = table.select("rec", condition);
            return getToExtractInfo(entries);
        }
        else
        {
            condition.EQ("to", company);
            entries = table.select("rec", condition);
            return getFromExtractInfo(entries);
        }
    }

    function select_Receipt_byID_another(Entries entries) private constant returns(string[], string[], string[], string[], int[], string[])
    {
        string[] memory from_list = new string[](uint256(entries.size()));
        string[] memory to_list = new string[](uint256(entries.size()));
        string[] memory cur_bill_list = new string[](uint256(entries.size()));
        string[] memory orig_bill_list = new string[](uint256(entries.size()));
        int[] memory status_list = new int[](uint256(entries.size()));
        string[] memory due_date_list = new string[](uint256(entries.size()));
        for(int i = 0; i < entries.size(); ++i)
        {
            Entry entry = entries.get(i);
            from_list[uint256(i)] = entry.getString("from");
            to_list[uint256(i)] = entry.getString("to");
            cur_bill_list[uint256(i)] = entry.getString("cur_bill");
            orig_bill_list[uint256(i)] = entry.getString("orig_bill");
            status_list[uint256(i)] = entry.getInt("status");
            due_date_list[uint256(i)] = entry.getString("due_date");
        }
        return (from_list, to_list, cur_bill_list, orig_bill_list, status_list, due_date_list);
    }

    function select_Receipt_byID(int id) public constant returns(string[], string[], string[], string[], int[], string[])
    {// 查看与id有关的交易信息
        Table table = openReceiptTable();
        Condition condition = table.newCondition();
        condition.EQ("index", "rec");
        condition.EQ("id", id);

        Entries entries;
        entries = table.select("rec", condition);
        return select_Receipt_byID_another(entries);
    }

    function get_id() private returns(int)
    {// 获取下一个id
        int counter = 0;
        Table table = openReceiptTable();
        Entries entries = table.select("rec", table.newCondition());
        counter += entries.size();
        return counter;
    }

    function insert_receipt(string _from, string _to, string _bill, int _status, string _dd) public returns(int)
    {// 插入新交易信息
        Table table = openReceiptTable();
        Entry entry_to_insert = table.newEntry();
        entry_to_insert.set("index", "rec");
        entry_to_insert.set("id", get_id());
        entry_to_insert.set("from", _from);
        entry_to_insert.set("to", _to);
        entry_to_insert.set("cur_bill", _bill);
        entry_to_insert.set("orig_bill", _bill);
        entry_to_insert.set("status", _status);
        entry_to_insert.set("due_date", _dd);
        int count = table.insert("rec", entry_to_insert);
        emit InsertResult(count);
        return count;
    }

    function update_receipt(int _id, string _from, string _to, string _cur_bill, string _orig_bill, int _status, string _dd) public returns(int)
    {// 更新交易信息
        Table table = openReceiptTable();
        Entry entry = table.newEntry();
        entry.set("id", _id);
        entry.set("from", _from);
        entry.set("to", _to);
        entry.set("cur_bill", _cur_bill);
        entry.set("orig_bill", _orig_bill);
        entry.set("status", _status);
        entry.set("due_date", _dd);
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        int count = table.update("rec", entry, condition);
        emit UpdateResult(count);
        return count;
    }

    function renew_credit(string _from, string _to, string from_credit, string to_credit) public returns(int)
    {// 更新交易双方信用值
        Table table = openCompanyTable();
        Condition condition=table.newCondition();
        Condition condition2=table.newCondition();
        condition.EQ("name", _from);
        condition2.EQ("name", _to);
        Entries entries = table.select("com", condition);
        Entries entries2 = table.select("com", condition2);
        if (entries.size()<=0 || entries2.size()<=0)// 双方必须存在
            return -4;
        Entry entry=entries.get(0);
        Entry entry2=entries2.get(0);
        // from信用值下降
        update_company(entry.getString("name"), entry.getAddress("address"), entry.getInt("status"), entry.getInt("level"), from_credit);
        // to信用值上升
        update_company(entry2.getString("name"), entry2.getAddress("address"), entry2.getInt("status"), entry2.getInt("level"), to_credit);
        return 0;
    }

    function purchase(string _from, string _to, string _bill, string _dd, address sender_address) public returns(int)
    {// 采购商品签订应收款项
        if(is_registered(_from) == -1 || is_registered(_to) == -1)// 双方必须存在且正常运营
            return -4;
        if (verify_account(_from, sender_address)!=1)// from发起purchase
            return -3;
        /*
        Table table = openCompanyTable();
        Condition condition = table.newCondition();
        condition.EQ("name", _from);
        Entries entries = table.select("com", condition);
        Entry entry = entries.get(0);
        if (_bill>entry.getInt("credit"))// bill不能超过信用值
        return -2;*/
        int count = insert_receipt(_from, _to, _bill, 0,_dd);// 提交账单
        if(count == 1)
            return 0;
        else
            return -1;
    }

    function repay(int _id, string _bill, int new_status, address sender_address) public returns(int)
    {// 偿还款项
        Table table = openReceiptTable();
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        condition.EQ("status", 1);// 账单必须已经生效
        Entries entries = table.select("rec", condition);
        if (entries.size()<=0)
            return -5;
        Entry entry = entries.get(0);

        if (verify_account(entry.getString("from"), sender_address)!=1)// 自己欠自己还
            return -3;

        update_receipt(entry.getInt("id"), entry.getString("from"), entry.getString("to"), _bill, entry.getString("orig_bill"), new_status, entry.getString("due_date"));
        // 信用值上升
        /*
        table = openCompanyTable();
        condition = table.newCondition();
        condition.EQ("name", entry.getString("from"));
        entries = table.select("com", condition);
        entry = entries.get(0);
        update_company(entry.getString("name"), entry.getAddress("address"), entry.getInt("status"), entry.getInt("level"), entry.getInt("credit") + _bill);
        */
        return 0;
    }

    function revoke_receipt(int _id, address sender_address) public returns (int)
    {//撤销交易
        Table table = openReceiptTable();
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        condition.EQ("status", 1);    // 已经失效的不能撤回
        Entries entries = table.select("rec", condition);
        if (entries.size()<=0)
            return -5;
        Entry entry = entries.get(0);
        if (verify_account(entry.getString("from"), sender_address)!=1 && verify_account(entry.getString("to"), sender_address)!=1)
            return -3;                    // 交易双方才能撤回
        /*
        if (entry.getInt("status")==1)// 若已经生效，恢复信用值
        renew_credit(entry.getString("to"), entry.getString("from"), entry.getString("cur_bill"));
        int left=entry.getString("orig_bill") - entry.getString("cur_bill");
        if (left>0)                   // 若不够还则创建反向交易
        insert_receipt(entry.getString("to"), entry.getString("from"), left, 1, entry.getString("due_date"));
        */
        // 原交易失效
        update_receipt(_id, entry.getString("from"), entry.getString("to"), entry.getString("cur_bill"), entry.getString("orig_bill"), 2, entry.getString("due_date"));
        return 0;
    }

    function transfer(int _id, string third_party, string update_bill, string new_bill, int update_status, address sender_address) public returns(int)
    {// 转让账单
        Table table = openReceiptTable();
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        condition.EQ("status", 1);// 生效的才能转让
        Entries entries = table.select("rec", condition);
        if (entries.size()<=0)
            return -5;
        Entry entry = entries.get(0);
        if(is_registered(entry.getString("from")) == -1 || is_registered(entry.getString("to")) == -1 || is_registered(third_party) == -1)
            return -4;
        if (verify_account(entry.getString("to"), sender_address)!=1)// 应得者才能转让
            return -3;
        /*
        int left=entry.getString("cur_bill") * del_bill;
        int newstatus=1;
        if (left<0)// 转让值不能超过现有值
        return -2;
        else if (left==0)// 全部转让完毕，原账单失效
        newstatus=2;
        */
        update_receipt(_id, entry.getString("from"), entry.getString("to"), update_bill, entry.getString("orig_bill"), update_status, entry.getString("due_date"));
        insert_receipt(entry.getString("from"), third_party, new_bill, 1, entry.getString("due_date"));
        //renew_credit(entry.getString("to"), third_party, _bill);
        return 0;
    }

    function finance(string _from, string _to, string _bill, string _dd, address sender_address) public returns(int)
    {// 使用信用融资
        return purchase(_from, _to, _bill, _dd, sender_address);
    }
    /*
        function finance2()(int _id, string third_party, int _bill, address sender_address) public returns(int)
        {// 使用单据融资
            Table table = openReceiptTable();
            Condition condition = table.newCondition();
            condition.EQ("id", _id);
            condition.EQ("status", 1);// 生效的才能转让
            Entries entries = table.select("rec", condition);
            if (entries.size()<=0)
            return -5;
            Entry entry = entries.get(0);
            if(is_registered(entry.getString("from")) == -1 || is_registered(entry.getString("to")) == -1 || is_registered(third_party) == -1)
            return -4;
            if (verify_account(entry.getString("to"), sender_address)!=1)// 应得者才能转让
            return -3;
            int left=entry.getString("cur_bill") - _bill;
            int newstatus=1;
            if (left<0)// 转让值不能超过现有值
            return -2;
            else if (left==0)// 全部转让完毕，原账单失效
            newstatus=2;
            update_receipt(_id, entry.getString("from"), entry.getString("to"), left, entry.getString("orig_bill"), newstatus, entry.getString("due_date"));
            insert_receipt(entry.getString("from"), third_party, _bill, 0, entry.getString("due_date"));
            return 0;
        }*/

    function set_bankrupt(string _name, address send_address) public returns(int)
    {// 破产
        Table table = openCompanyTable();
        Condition condition1 = table.newCondition();
        condition1.EQ("name", _name);
        condition1.EQ("status", 0);
        Entries entries = table.select("com", condition1);
        if (entries.size()<=0)
            return -1;

        if (verify_account(_name, send_address)!=1 && isBank(send_address)!=1)// 只能自己或金融机构宣布自己破产
            return -3;
        Entry old_entry = entries.get(0);
        Entry new_entry = table.newEntry();
        new_entry.set("status", int(1));//破产
        new_entry.set("name", _name);
        new_entry.set("address", old_entry.getAddress("address"));
        new_entry.set("level", old_entry.getInt("level"));
        new_entry.set("credit", old_entry.getString("credit"));

        Condition condition2 = table.newCondition();
        condition2.EQ("name", _name);
        int count = table.update("com", new_entry, condition2);
        emit UpdateResult(count);
        return count;
    }

    function isBank(address Myaddress) private constant returns(int)
    {// 验证是否是金融机构
        Table table = openCompanyTable();
        Condition condition = table.newCondition();
        condition.EQ("level", 1);
        condition.EQ("status", 0);
        Entries entries = table.select("com", condition);
        int flag=-1;
        for (int i=0;i<entries.size();i++)
        {
            Entry entry=entries.get(i);
            if (entry.getAddress("address")==Myaddress)
            {
                flag=1;
                break;
            }
        }
        return flag;
    }

    function verify_trade(int id, int choice, address sender_address) public returns(int)
    {// 审批交易数据
        if (isBank(sender_address)==-1)
            return -3;
        Table table=openReceiptTable();
        Condition condition = table.newCondition();
        condition.EQ("id", id);
        Entries entries = table.select("rec", condition);
        int count=0;
        for (int i=0;i<entries.size();i++)
        {
            Entry entry=entries.get(i);
            update_receipt(entry.getInt("id"), entry.getString("from"), entry.getString("to"), entry.getString("cur_bill"), entry.getString("orig_bill"), choice, entry.getString("due_date"));
            /*
            if (choice==1)
            renew_credit(entry.getString("from"), entry.getString("to"), entry.getString("cur_bill"));
            */
            count++;
        }
        return count;
    }
}

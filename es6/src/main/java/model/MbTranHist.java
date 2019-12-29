package model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MbTranHist {
    private String seqNo;

    private Long internalKey;

    private String tranDate;

    private String tranType;

    private String eventType;

    private String ccy;

    private String crDrMaintInd;

    private BigDecimal tranAmt;

    private String branch;

    private String sourceType;

    private String terminalId;

    private String userId;

    private String reference;

    private String tranCategory;

    private String bankSeqNo;

    private String effectDate;

    private String acctTranFlag;

    private String reversalTranType;

    private String reversalDate;

    private BigDecimal previousBalAmt;

    private BigDecimal actualBalAmt;

    private String baseAcctNo;

    private String acctSeqNo;

    private String acctCcy;

    private String prodType;

    private String acctBranch;

    private String glCode;

    private String acctDesc;

    private String traceId;

    private String narrative;

    private String authUserId;

    private String apprUserId;

    private String tranDesc;

    private String tranTimestamp;

    private String cashItem;

    private String tranNote;

    private String tranStatus;

    private String acctRealFlag;

    private String clientNo;

    private String clientName;

    private String profitCentre;

    private String businessUnit;

    private String sourceModule;

    private String receiptNo;

    private String primaryTranSeqNo;

    private String rateType;

    private String othSeqNo;

    private Long othInternalKey;

    private String othBranch;

    private String othBankName;

    private String othBankCode;

    private String othBaseAcctNo;

    private String othAcctSeqNo;

    private String othProdType;

    private String othAcctCcy;

    private String othAcctDesc;

    private String othReference;

    private String servCharge;

    private String sortPriority;

    private String fhSeqNo;

    private String docType;

    private String prefix;

    private String voucherNo;

    private String balType;

    private String printCnt;

    private String contraCcy;

    private BigDecimal contraEquivAmt;

    private BigDecimal baseEquivAmt;

    private String documentType;

    private String documentId;

    private BigDecimal crossRate;

    private String channelSeqNo;

    private String settlementDate;

    private String contrastBatNo;

    private String bizType;

    private String rateFlag;

    private String fromCcy;

    private BigDecimal fromAmount;

    private String fromRateFlag;

    private BigDecimal fromXrate;

    private String fromId;

    private String toCcy;

    private BigDecimal toAmount;

    private String toRateFlag;

    private BigDecimal toXrate;

    private String toId;

    private BigDecimal ovCrossRate;

    private BigDecimal ovToAmount;

    private String pbkUpdFlag;

    private String company;

    private String lender;

    private String clientType;

    private String amtType;

    private String acctStatus;

    private String accountingStatus;

    private String withdrawalType;

    private String reversal;

    private String payUnit;

    private String commissionClientName;

    private String commissionPhone;

    private String programId;

    private String primaryEventType;

    private String amtCalcType;

    private String glPosted;

    private Long tranTime;

    private String routerKey;

    private String tranMethod;

    private String channelType;

    private String reversalSeqNo;

    private String limitRef;

    private String contraAcctNo;

    private String contraAcctName;

    private String contraBankCode;

    private String contraBankName;

    private String bak1;

    private String bak2;

    private String businessInfo;
}
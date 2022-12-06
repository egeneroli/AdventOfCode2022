/**
 * 
 */
package AdventOfCode2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/****************************************************************************
* <b>Title</b>: Day3.java
* <b>Project</b>: lib
* <b>Package</b>: AdventOfCode2022
* <b>Description: </b> FILL IN DESCRIPTION HERE
* 
* @author egeneroli
* @version 1.0
* @since Dec 4, 2022
* @updates:
****************************************************************************/
public class Day3 {
	
	public int calculate2(String input) {
		String[] inputArray = input.split("\n");
		
		int totalPriority = 0;
		for (int i = 0; i < inputArray.length; i+=3) {
			//System.out.println(i);
			String[] group = new String[3];
			for (int j=0; j < 3; j++) {
				//System.out.println(i+j);
				group[j] = inputArray[i+j];
			}
			//System.out.println();
			
			List<Character> commonList = getCommonChars(group);
			char commonChar = commonList.get(0);
			
			totalPriority += priorityMap.get(commonChar);
		}
		
		
		return totalPriority;
	}
	
	public int calculate(String input) {
		// parse input into list/array, iterate through each line, split into halves, determine shared char, add priority of char to total
		int totalPriority = 0;
		for (String s: input.split("\n")) {
			//String half1 = s.substring(0, s.length()/2);
			//String half2 = s.substring(s.length()/2);
			
			//List<Character> half1List = stringToCharList(s.substring(0, s.length()/2));
			//List<Character> half2List = stringToCharList(s.substring(s.length()/2));
			
			//List<Character> commonList = new ArrayList<>(half1List);
			//commonList.retainAll(half2List);
			List<Character> commonList = getCommonChars(new String[] {s.substring(0, s.length()/2), s.substring(s.length()/2)});
			
			char commonChar = commonList.get(0);
			
			totalPriority += priorityMap.get(commonChar);
			
		}

		return totalPriority;
	}
	
	public List<Character> stringToCharList(String s) {
		return s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
	}
	
	public List<Character> getCommonChars(String[] stringArray) {
		List<Character> commonList = new ArrayList<>(stringToCharList(stringArray[0]));
		for (String s: stringArray) {
			commonList.retainAll(stringToCharList(s));
		}
		return commonList;
	}
	
	public void initPriorityMap() {
		priorityMap = new HashMap<>();
		for (int i = 1; i < 27; i++) {
			priorityMap.put((char) (i + 96), i);
			priorityMap.put((char) (i + 64), i+26);
		}
	}
	
	public void run() {
		String input = "jLnFTjhwFTLFDGDDvLgvDssBJBbVRNZJPPJBGzBNRVJNRB\n"
				+ "QWmffSmMZCfWrmHlCflQWfSNBpVBNbPSbbJNppcVVzzpcp\n"
				+ "lflrqrWMmfdMlrtWWmZgDjsqwFwhFDsngnvhqs\n"
				+ "CNBGwsWVsdWDNCCVvfPLfQfgZHPBvpgf\n"
				+ "qhJqJlmcFnhJclllPgZjvpHpvfZpPbLn\n"
				+ "lpMJJhhcSMDMMwws\n"
				+ "jphtLMfVpjJRjJscMJptssnrwdNwQrNbwNzQTbcNrwQT\n"
				+ "gSPPSqGGGrdLrNQGrr\n"
				+ "PvWPDvWWCSSBBZPjLVDfhjsLLsDhtR\n"
				+ "hlLwDGLNnTrbNdtbdb\n"
				+ "ZfsvJbsJTrsttHTR\n"
				+ "vjPQJPFcmZjVFZFvbbVcJZJPBBhLMnDBznnGMDLMwBhlmBBl\n"
				+ "JdTJFtwHjJTzPMqPccqzjB\n"
				+ "rggffSCvNBCbWBWt\n"
				+ "ZsSpnnRpLStrZpTDwTVwGTJHDLdH\n"
				+ "sZqswsrcRpCrsCsrrbtpbpDlttlFbbpGtj\n"
				+ "LhNgNvNNhTQMhQMWhvvhfWhllbGGPbPtlPmFlDbjHHdlmg\n"
				+ "ffNBNMhDvQDhvzhBhNTBnSzzrZCZnrsVCSwcnrCC\n"
				+ "hHGhjLjwtFGwGhhhhtwjtlhrDBQrRBHczRcMvsBcRrDDMH\n"
				+ "TTfbZdZbPbVCmCdmnZmCVdfrJBczMrrznvBvQBzzzcBscn\n"
				+ "fdbPZTVVfSdmbfdvjjSGljhhtNGGhL\n"
				+ "fMrGQbPrbnfljjzVLQzNCzVv\n"
				+ "WcDqlSpctSJNzSFJLCzN\n"
				+ "hBBWcHtWgWDmZlwwrsPrRTRTMrmn\n"
				+ "ZJTVdgpzPpLVVwZgPzTPLwgWdhcWhSQCbcSbBtSWdSWchb\n"
				+ "qnDGnNjqDqNlqsCSWhMtlMltWSQB\n"
				+ "NnNrFQGjDRgLrgrpPr\n"
				+ "nCrhsmVrlrzsTvsnVvmTTlpvPJPWWMPJWWPCJMPNPgPJMjJj\n"
				+ "ctDqBLHLRLBSBqFRDBFqFFNGgjjrGGMrWftWJPGgwfGN\n"
				+ "HFcqZqqdcFQRQBdrlnplhspQlTmmvlph\n"
				+ "mllQMvMTMMdNNdTzTGmLgWjBgGtBqWHhGWjHtB\n"
				+ "PPsbbZPCVfJFJRJZsBHjjHhHcjqSSWBt\n"
				+ "wCVJFVRJDDpCPqZlTlwQnrvzmMdmml\n"
				+ "JfCJtCwfsffBJmFmgFmFhhdwgV\n"
				+ "lzHzsSHvSRlDRjlHTgqVVmmVPZqqmggmqS\n"
				+ "GzNNRTWvbWGvGQtfLnbfMbJfsC\n"
				+ "FnPrwqrFqsPrrsrwqwnbZFPwBQMhhdBHQZhTVBMdDHVhQQVh\n"
				+ "zlLWlLvLgtgbMdhfVDdzfd\n"
				+ "NWRNjLmWblGFsssNnSwPsw\n"
				+ "gnmCjzwnmCPTPhBwPjzBgqPjllJJSWlhfhQDSrpJRhDSlfJl\n"
				+ "rLHNHrLHVNbVHMMctZFHsbcsDSDWpSDSGfSRsRWSRllfGSSG\n"
				+ "NNtdMVrLNdZNvLvLZrzCndqBgwwPmwgjggBn\n"
				+ "tGSBRwwStBftfBHRScHwfcrCgQgDnjQhQDqgBqgCCDnnqg\n"
				+ "LNlbZmlPVNzWlLZVFPQqgDTQTmjqCQDQqmsQ\n"
				+ "bpZNZLNNZlLNLZNNZbLlbLZbwrwSHGJSwrGwRtRpcjjtMttw\n"
				+ "JvGNPWhLPLBffwhGCrbgnFDbgcZwbVnb\n"
				+ "TqzSTlQzdHQQpTTjSddHTSnbqnZDcrrFnFVFVbVVcbhc\n"
				+ "mlpdjhhTHWBmWWBJvG\n"
				+ "ScrppccsbMRRvltvrvrj\n"
				+ "HRTVTnDwDLJJJZwwVQTmNlvqBNtvWBBBQvhNfqlB\n"
				+ "TLJdLLCJZHJCVHHTwRdcsGpSbgsczcdcgS\n"
				+ "tffHzJctHsSHHHGTtzvttZFlZZhVjglVmgDhDdjgjS\n"
				+ "qCBMCqbpqfQPMBPpPljQVDDZdddZdgFlDV\n"
				+ "fWwLfbbWNzGGrzvWHJ\n"
				+ "VnNFqrrJdfQhCDFPhttD\n"
				+ "pGpTTzHmRTNTTLRRtbBBbzhPtbtbPPBt\n"
				+ "HLpHpmSRHTpllcLTSgVvNrJVfJvqMnZN\n"
				+ "tHzgwJCgzZlLFLnWJTnc\n"
				+ "NnNnmsMnBDFSFWdqssFd\n"
				+ "mDMBNmRRjjMNnvrPnjjRQQvfRgwpgHHpppHhZbzfCpbHtH\n"
				+ "TTwBnnZwBnNQZZWLWTTFNFbsCGrGFCNvDDDGFj\n"
				+ "lzPRfVVRMclPRMRcbFDjfCCGvvsdGCDd\n"
				+ "hSmmlMlVztlhRlVbqQpLnWqSqLZZwZQT\n"
				+ "zzVTcqmVqzdmhZJbFpQZQQtLLWLLbp\n"
				+ "TGwvGgTnGTRHLNtMftWHNbWf\n"
				+ "vljTGwvvlCljsgSnCBslDPPJrPdVzrSmhdqhVDPP\n"
				+ "LzCGCGLBGNlLmFFmmSSBBqSc\n"
				+ "ZJfbdfbWtbgVlblJtjcFcqTmFqqjtFHtjj\n"
				+ "QgZhZgQVdlfddlWPhfvMCGRGPDMMRvDNGvND\n"
				+ "nLdpLJSSnLMwjCDwnQhw\n"
				+ "NZGNgmsgPtPTgWqqCGwRbrDCQbCwhMjr\n"
				+ "gNqPTqTPttPZqcWfHLlBvccFvhpvJc\n"
				+ "GNdGhMMvRBjmMVjM\n"
				+ "trcZJJFmCgfZtcrQCfCZSpqSRjBQpQRQBbBqsSqR\n"
				+ "JcTmwtTtcrmThLGDhNTThvNW\n"
				+ "ZqhmtzzfZRsSfRmSTThMpLNgpgJQThMG\n"
				+ "jdnvdHWFvvjFdCWFrvVVnvFDQrJJMpgMJGNLJNJTPPgQGJGR\n"
				+ "vnHjDVHBWjdncDRbRzzzmmBsSSzt\n"
				+ "VZNZSCPTJPpvNTNsCrbnVrLccbLnnRVfWb\n"
				+ "MlqQBlBztFlhQJdFhccbhnRWWfnggfGc\n"
				+ "lzJqllwQtmzdHZpvwjPPNspj\n"
				+ "wTnCMnwQCQvTTJdfqhdtrrSmhttmmm\n"
				+ "LZlZglZBvjgbHHSrHpbr\n"
				+ "GWvPjPNGljBzMzJVMwNJMw\n"
				+ "CSlChlvPPGvdddqPqSJhPdbNbcMTMTbDbcRNDFcFLMLJ\n"
				+ "HWjgngmBZGQgRRNbFRcHbbzT\n"
				+ "BmnpnQBBssBsQBnPptSStllGhdCGrS\n"
				+ "nhQCnCvjchBBjMNrwbRwZvvSvSWW\n"
				+ "qgHlfqblVbPfVqlbGgPGfdfLSdNssWWLZsNsLZsdNdLSZZ\n"
				+ "gbGHzzbzHtPfcJhczmMnQBJn\n"
				+ "TTGJJPPZJGRzDwtQjTjzDC\n"
				+ "VbnrmNVWVWbffbVMsrbrnvFRjzCzQCjvQDDStDFS\n"
				+ "MhrRfMmfMmsHgsqPLLGdBqJJGHqd\n"
				+ "FMCMbmSQTHdCBmpFHprdHcDNqqfqJdJsDcclDDZlNf\n"
				+ "wzwRjwwtRhnzjRBWRnvtnWtnNsfsVDlDlsqVNNNqcqVlDvqf\n"
				+ "PtnGWjtwLBwtjCFPFpMpMbSrQm\n"
				+ "PWzrtrtzBBfjjWGbfNWvLJLgVSjRLVlSlllLpS\n"
				+ "HhZmHGGhGZRpQlpLpL\n"
				+ "dTdnHCnhmhcHcntrPPGfzfrcNtPz\n"
				+ "PfmWWWPwWdPdWPTVCdpjRnzhjDbrFnGbnQGQrrDR\n"
				+ "LSBnZLnJBQDLRGcQGc\n"
				+ "NHNqqBqvMMBsJgNvgsstBHNsdwwfTtwdpfTTmVmnWmtmWVlp\n"
				+ "zjzwTHCzDzbDzNTDjzDCCHRZmmSGmfgPmZGtHtgLmf\n"
				+ "hsqJMcQltLtPhLgL\n"
				+ "QWWWcMsWFJssrnsWrdlcPBwNTdzwTvvBvjwpTbBzpT\n"
				+ "LRvWZdLzzZWSqdpBzLdzLgRmbHPVHtFHMFFMCtHCfHcbVcqb\n"
				+ "sGGjNDnGTnlSSwsrTwcFVMHwCPPPcVfVHwPM\n"
				+ "GDQsDlJlQhSDTrpvhhWBLWvdRvWh\n"
				+ "sWhhdhdhbHSVBbFLZLnnCmCmtTTCCV\n"
				+ "RcQvjNrcrwGNcGNRJJmTpppgCCgppNgTtFmp\n"
				+ "GRRRGRGclfjwRsbfHFhWBDhdFs\n"
				+ "jfRTfgHqgDZHClcPcrGhnbcqrr\n"
				+ "FztdMLGBzpFJpcnzzclblcnbbl\n"
				+ "pdNBtdNLdvsNtdLpdGLTZDTgDjmvCZjCRCDDHg\n"
				+ "bdrbdZbJtZhrMrWRHHvRHRvHllqZ\n"
				+ "GGjCNgDVDFsCCwFNHjlrPrlSRWPcSlvl\n"
				+ "CNVDgDwVrQggDDQVsDwNwgTQdJtbfbLhtTdtLmnfbJMntnft\n"
				+ "mjznjLchnPPcLLnwCTFFvPgJJWdqJqJJdJJr\n"
				+ "bMlQRzbSBbRBMlMfMBBZFrqggvVvfqdDVDVDWWFd\n"
				+ "ZSSsRGQtSpBBpZMSbSpZsbtsjhCHTHcLCcjzjCwTTHhmNHtT\n"
				+ "ThCHLCfVfDTtDnDppTqclFfSclNrwNPwNfFr\n"
				+ "JmsQjJPJMWRRMddZGZWsdsMZrmcqvNSFSwrScrvwSwcccFFw\n"
				+ "ZWdJQGGzPJsMRZBGtHLgCHTTVTLBDtgn\n"
				+ "JVGJfttHgTLVTsnHgVHfwVBDjQDNQSjQwjbNbQjBbddS\n"
				+ "rPvZcZpcvtzcZddmNQjWQSjDpp\n"
				+ "rrRZPzRMztFFFqMZFtRVgnsLnVHfVHqJnghHJL\n"
				+ "grrZZbJSggQDLgrvSSDBVsGdjHjnGnBGVsLGHj\n"
				+ "pCtWtPtffPRcWdBsGsGRBRvhBj\n"
				+ "zmvNNclfZJJFZQbN\n"
				+ "DDqCgPPDgtDSPPDbgCqcmBMBTQLQZBWwQZbsMWZZMw\n"
				+ "RNlNvpvNvndNjMZtBpwMTWLBZt\n"
				+ "VlNJvRzdlGFRFGRRRjFVJRRggtPPHgHzgStqCcHHmHqcrc\n"
				+ "RfmdFgbtFLHlsjRPsL\n"
				+ "LqrhvCTvNNhWBvBTrThrDWZrPjMQPQMcjzsPcQHTlVHQjcMM\n"
				+ "BhhrJWJLvCbfJbwgtgJn\n"
				+ "FWpRfFRjtjFpVvbTTZFPhJhJLdCLdWBhPmHmCW\n"
				+ "McMzNlGrgGGsHcqmdLqbhJmd\n"
				+ "lNMwsnMzzsSSGtVVSfvZTtSppb\n"
				+ "JrJQDJDVvGwGVwvcGCRRcCBBqsWBRZqWZZ\n"
				+ "bNdmhvMpjmHbNbpNvmbBqlRWlCtCqlRZqRtW\n"
				+ "HmTjNdmMfTTVvLSzvz\n"
				+ "WMQWmfGfQfVnMGWGmGtGWHzZtdLzpZhwrtZcwZzdps\n"
				+ "jBgljNPbNjPFRbggNlNBCcHsrLLLHlrLslhlzdhdHppp\n"
				+ "BBPBJbFCTGJcVQMGMv\n"
				+ "fdmfmmjbPmjlmfhfGglTNLCNvPNSpPvpvSLCPCpS\n"
				+ "MtJwMrwrZJVHMgqJHtwVJzVVLCvpNLvDrLvQSQSNcCNpFNLS\n"
				+ "wWMVWZzqqMRHztwJZzRqVtjGlBdhbGGbThhTlnTBTWgb\n"
				+ "ShhrjPrvhlljnHrggPvvSqRMBZZqBTZmMmTmTMdD\n"
				+ "ctLzbcpWCJbNWRQqBBdZFMBdfZdJBm\n"
				+ "GQWpzLcVpVNpRggHrhhgvnGPwj\n"
				+ "PdzSrmmqSgWWddNQds\n"
				+ "TZbwCTLZbHLWptQHhpgNsc\n"
				+ "MZjBLjMLDTDqrrGflsjjrf\n"
				+ "DBHTnlGGBPjPmwRWhn\n"
				+ "hgLgZccSrcMgLMSpCLJjNWmPWjmVWdwJjd\n"
				+ "MfQMScQrMSprrTGHBhqTvqlGQT\n"
				+ "CDvLRCCzQDWzcGQGvRcpVFVsgTsFrgllrTpF\n"
				+ "mjbqtHqHbspspnMv\n"
				+ "ZvvqdqqHvLhDhJWzJd\n"
				+ "ThHnbRTVbQHSWvBVVBCPBW\n"
				+ "FFJgfqfqNwNrvDCBNppP\n"
				+ "jzsFfqwqssLzJjffJcssHQHQdnMdhTGHLbddbCnn\n"
				+ "gwMHHhbSwnqHrgMqMbnBcldhzBLQBQhBLtLttQ\n"
				+ "dCRmpfvpCzcQllflQz\n"
				+ "TjPsCJmvZPNPsvZpPTTRCpprjdbrHngqDwqMrDnMbMbHgM\n"
				+ "CgNQpgTCgNltHTNQlHpRnRnzBfJnPnmpcpfq\n"
				+ "LGrrvGSMGvrsWPBmnJPmPfmrqr\n"
				+ "VGsSvdJvbSHVtlttNVHT\n"
				+ "LnZDtzpzNpPsCbfBbDCDBC\n"
				+ "gSGRSSghqhhSvSRvvhvVqFJdsfBsCHJbVfddCJ\n"
				+ "vglRRjQSmnQQCNrL\n"
				+ "HwhVddVVwRcGHGjlfS\n"
				+ "MbBvDBGvWqpNWvMbzDMNZZlLcjlSclflcjqgfCRg\n"
				+ "bvDWDWQbspPPWWQMnGdnFnJtJFGJhhPJ\n"
				+ "nDJDdQdPDlDJnnbRQDlTqVfFwfVtjJjjFfqqFqgN\n"
				+ "hScrSmSCTZvZCSmZLFwfffqgjfhNGwjhfw\n"
				+ "zCzsCCTcMBmSLmsMWHdPWRQPQPddDQ\n"
				+ "lzGflPZLSqSlccpqjpdNCwjdsjCnCsJp\n"
				+ "VghPQDVMFQNdJjRNBw\n"
				+ "tVrhDVmHTlPlGrzL\n"
				+ "gfSJJlzvGgRPpggR\n"
				+ "VLDDHhHtlZnnZqbGmtqPmmmmWN\n"
				+ "VVDBnddDBFFzlscC\n"
				+ "cmmzRzcRjFrGncTHqc\n"
				+ "ghJfwJJvqhbrdndWgddWBH\n"
				+ "fQbSpJwSthbhtvpCwqQbsDPMNLlpRmplzPlRMPzM\n"
				+ "bBVQDgBpTzbhpgQVhTmBVBzgrvcnrncsnZrwzHJZfnHcZfrP\n"
				+ "tdWlFLMHGFGRGMRLNFHlfwJcrPwcsnfwcvZPfr\n"
				+ "RLWGjHLjSjtjmBQVgpQhhg\n"
				+ "ttRRJBrCfpprmFqSJVTSwNJNTWlVTn\n"
				+ "nLsMghDbQdnhwWNLjcwNSlVH\n"
				+ "ZZDgnDQvDdndPCmfrRqzvFqpmp\n"
				+ "PPzCrcGGlnhPcsdfNZZdpLLNZsfd\n"
				+ "RjjWHjVWDvjWqVMHjHJjVlHdwwJbLfNZdfdgfpppgpfpZw\n"
				+ "lBVvqjQqBDBlVmCFGChhnSmTTrmr\n"
				+ "BZnGZFvGvBGGrwLBZlzNgmzMhMdfmwgmdmmt\n"
				+ "cpDHtVjDWjCtDTCcDNhhzNJszgzMTJhmgg\n"
				+ "VWWcRWDpPDPPtHtScqFrLlBFFFSFBqZLSF\n"
				+ "qBFcCGFcHHGBldzBLtdzSrdR\n"
				+ "fpmfpswfQNQmfPHfPPgJNwRtngTtSTRzdTrLRnSnldLd\n"
				+ "fsHNpNPwQZwhVHMFGqvvMcqWFGVF\n"
				+ "FwFJlqDDwwgFHQdmFmSQ\n"
				+ "pPtzcrLHZrQfdsBsdLdf\n"
				+ "bHrrHjbrjbrJvDvbqMVNqJ\n"
				+ "WVVpPHPfWnHmpccpnpvQcrgNNMSCTMjMgjjMMN\n"
				+ "RsZbhRFTDDJtZFJhTLRRLMrMrCdSNCdSMjrNhjNGSQ\n"
				+ "LLqtqqzwqmmflPTq\n"
				+ "mmLhdZSdqhqnqMZShLQrMWcrcWQwTgrWVVWc\n"
				+ "CCvjjsfJRCCJbDbFsbGJvzgWWGlWrWlwTQrwQGGcHlBg\n"
				+ "NRDbRCNsNjQPSSLZNdLn\n"
				+ "cmVLcQZPSHBGlSddBf\n"
				+ "DFNzDNszlbBlgHNN\n"
				+ "tWRzFjsWWtrtlDztzwjDjThLPhRMpppLMhmMhnmncRcn\n"
				+ "LFFcgdNLpLQggZQMFMcNJggRHCHsTtRtHTzCTZHRsfSRfs\n"
				+ "qnhlhmGrrhhnqdmGhdhWbnSRzSfsBRSTSRtBflRffzfz\n"
				+ "PnbmrmGjWMVNvdPFLp\n"
				+ "swcWsMMmVwWwFtWlbnnZnFBhbNfrhN\n"
				+ "dQLZHjpDQCfBBBBTGTTC\n"
				+ "DJvDpDSzSDQZMWsWwPqVtwcS\n"
				+ "NVTMjjGGNBqhNttdTTjtcqjCfDlhZhSWCFFJmSmZmWJhmZ\n"
				+ "rznsHPnRLzwspZDWfGZJSLZZDC\n"
				+ "QzPRRvRQszQsGQcVNqTBBdVj\n"
				+ "rHGBglBNLpbqCgFC\n"
				+ "PbmbRnmMSbRPDWRnDVSMPMWmvvcvqqppTLZZFpvCpczLcCVv\n"
				+ "dsdMSPtMdPnSffQwGGsBBbQw\n"
				+ "nqjTlGPGnqWsMPPwGdGjQvQvJCCQBvmvCvQJtmCw\n"
				+ "DDhVbDrcVVFZLzHrZBRNCWStmQWztJvtCm\n"
				+ "VHDLhFFDpcHZcHGWfsGlspTTMqps\n"
				+ "FTPZwBzHHFLTTSwHLJTwVQqnDrzgRRVgQpDVRprn\n"
				+ "jJMjsWlvQqVrqgWW\n"
				+ "CjNsjjvlmslbcJmmbtCtCThdwhHLhhhLPdBCSPFh\n"
				+ "jfgllWfLrfmlTjsCjcjDNNDnNcHJnd\n"
				+ "MMBRSRZGppGFpBbbBGvVnFHhcCJqNcNcHCFNgnnn\n"
				+ "pMZZgBRVZVGRtRSMbMbvRQmTswWfzLzLzWLrPQmfwLlW\n"
				+ "tzpdHLNpfpPnlNFHnpssbgShtqcgTTggrsqg\n"
				+ "JZwWGCWMCcbsSHgWjq\n"
				+ "QCmDZCJwmvZvCVCVRQGCDMFBNlfHmfLLzPPfdpfBLlPB\n"
				+ "PCvRTdPPBVBTGgLGrGqMrMnv\n"
				+ "twQwStrsQwstjSHmmpqZpwqNGnGnNnZgLN\n"
				+ "mJScfjbttQjQtjcmfbbQJHFTBfdfRhhrPhhTfPVVhPPB\n"
				+ "DMPJmLLjhJwNBCNBww\n"
				+ "TpccnsZsbTHpsTHVszbpbGHSNwBtWlfrtCtCfrtlBlFZlf\n"
				+ "HsHvccvzbqsGzqnnHvLQPghQNNDDvLLjdjLR\n"
				+ "zmgVMrrjjMGbbzzdqqrgVbRTHqTTThpNHphNTvvvTNnv\n"
				+ "QLSmZmSJTvTnZTvn\n"
				+ "SfDtDDmQCFDwWmSrjPPGfjMjjGfMgV\n"
				+ "hdNHNHjnGHMTcpWlhBchRBJR\n"
				+ "fPztCTPtttLwLzVbwqSVtCBBDpWvlcRRlBpbDBQWcDWJ\n"
				+ "ftrCVzfTCFFzTFPrPjNZFHnNsMdZgjHdss\n"
				+ "TVcTbdqLqzHHRNHM\n"
				+ "PppQTWffPfCPQSsCprzRwRJDzMDzNwRBRPHN\n"
				+ "pjCTZjTjmTtsZhGcGdLvdlgv\n"
				+ "VJRhVfJRBgbdrrrvJpJJ\n"
				+ "cCSSHSHnZPwDFNHCDCQdbQQdvqnMsrpqQnvq\n"
				+ "NFFCwSrDzCNrwhtgzhWtBhgzWB\n"
				+ "RRBRczzcvmfzVRzVPvPTlCWnnlTGqGMgTTnG\n"
				+ "tNLdSdNSMFhsFttddtjlwGqllqQglqTWqwnThT\n"
				+ "rLSsLrNdZbbHjLdSFFMjLLtSfVRJzVJJVZcRfvZVRfDpVvRz\n"
				+ "DBsdssbdbDfPnnqzzJbqStqzjjJS\n"
				+ "RcGMGwwmrJjFFHPG\n"
				+ "mCgwMLPgCPmmwWDBDZvWvBCWfZ\n"
				+ "cJCGJJMZcMGWMhWCJTfFSfDmmgFmtshggffr\n"
				+ "vBVQBDbNstQmFsfm\n"
				+ "RVwHNblzwHvNdvBwNjVHGZJZLJTMLMJDZLGRMDGG\n"
				+ "LNCssCldDbrnNtVWNQ\n"
				+ "mmcpRBMBRBTZzgMMhtFFvrWQrQmWFtvn\n"
				+ "MZTgzMZSzTfSMDHwCfPlLLDnwL\n"
				+ "HlPcjMSHsMSPbgLhFPFNzP\n"
				+ "ZfRhtVdGBNLFzLqf\n"
				+ "DRtGGtRGJJTJDZDGdCJrVJmlWvHWcvjMsMWmhQsmCMjW\n"
				+ "swpQcsBvvHFpBHHcCHJvJddvbbWPdzhzzqqWdhPnhP\n"
				+ "gDDGTgjNWZTMTNjjggRLggldSPqSqGznnhnbdfnbqfhh\n"
				+ "WDMDtDNDctVFJstJ\n"
				+ "rFDbGjrGbpBBbrCbrwpvvZQZZhgTLVVVVLgThCTggg\n"
				+ "NlfHpttqzltMzRSMSdWRNSfSTTmqgcLgQVcQTcmgQgmZVPVZ\n"
				+ "HWSWpMHNpNWlpNMsMzWGGwwBvvwBnwBvsnnvnB\n"
				+ "mZVVHmmRZlvdjvNpHh\n"
				+ "DFBnFDtnFbBMrQbBMvFLvgtjjNpcLpJdNzJJdhWNpJNWcN\n"
				+ "QTsbrQggBsvsgVTwRVGZCfGZfR\n"
				+ "RGNGlGTWJnJlMcRMGqvSmnDtvDDvtLSLvr\n"
				+ "HfsFhPPdVshfbggDVmzcvSwvStVzDq\n"
				+ "BPPfCgCFfcBbPbHbBHZPFsbBpMGlClRNJRlQTjQWjWpTTlGT\n"
				+ "HQNqQbbRQHbZPZQPqbZTvWFWmCjvFRvRTSrRrr\n"
				+ "lBpBpzLLdBwsLwLLWNggzCFTCmFgWmrF\n"
				+ "VwsswGVBfJfccLBcsJGNdpJHZZqnhtqqnhPHDZHVtPQMZn\n"
				+ "LMHnHnPMnFdJFNFJjZvrJF\n"
				+ "wlTlslptlwtzcvTqvTsVSWWrbJfmJZNZJjcNWfNJmf\n"
				+ "qDlqVthTztSSpSlqtpTTzLvRRnnCvRCPLBQGDBdDGD\n"
				+ "HlmlJgRBBpJLpPBHnlLJsrgJDMttvSdddcMSStWjjWtcSzjs\n"
				+ "qfTbNPTVGhGFCVGTwZfNZNNVWvjSczcjStMMcdSczzczvbcW\n"
				+ "GZVqffQTwNQTmPRlggHLprHQ\n"
				+ "BjsWschcTjjWclQTSnZFrjtFtbzZfqntzF\n"
				+ "qggNGMHgvHMRZMtznbrftMCn\n"
				+ "qvHdqpwgJTsVccwscT\n"
				+ "FJwNPHJmPHTJHglTFwgmwqtShBqDmDBzzBzWhCDWqQ\n"
				+ "fpsjNsMVLVjDCCSzBtjztt\n"
				+ "sLrdfbsMcbMRZZZbbsfrsrNgGNglRwllRnJnwFvnFHnG\n"
				+ "lWWVTJlJCTTzDdWzqdtFFt\n"
				+ "PvPvrhbjhgSjvJQSnQRbjZDZFFHMtGGqFtRMGZFtGH\n"
				+ "rhPrSgfvJPfmwsTpLcsV\n";
		int result = calculate2(input);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		Day3 kata = new Day3();
		kata.run();
	}
	
	public Day3() {
		initPriorityMap();
	}
	
	Map<Character, Integer> priorityMap;
}

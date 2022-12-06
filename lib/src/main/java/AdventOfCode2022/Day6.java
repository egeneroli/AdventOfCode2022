/**
 * 
 */
package AdventOfCode2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
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
public class Day6 {
	
	public int calculate2(String input) {

		return 0;
	}
	
	public int calculate(String input, int n) {
		// iterate through string indexes, get substring(i, i+4), check if contains duplicates
		/*
		for (int i = 0; i < input.length()-3; i++) {
			String slice = input.substring(i, i+4);
			System.out.println("i: " + i + " | slice: " +slice);
			if (!containsDuplicates(slice)) {
				System.out.println("no duplicates");
				return i + 4;
			}
		}
		*/
		
		int i = 0;
		String slice;
		do {
			slice = input.substring(i, i+n);
			i++;
		} while (containsDuplicates(slice) && i < input.length()-(n-1));
		
		return i + (n-1);
	}
	
	public boolean containsDuplicates(String s) {
		for (int i = 0; i < s.length()-1; i++) {
			for ( int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) return true;
			}
		}
		
		return false;
	}
	
	public void run() {
		var result = calculate(input, 14);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		Day6 kata = new Day6();
		kata.run();
	}
	
	public Day6() {
		readInput();
	}
	
	String input;
	
	public void readInput() {
		input = 
				"sbpbwwrlwrwggscsfcsshsvhshphttzctztfflddvbvcbcvbcbnbrrstsspsvpvllncccqssqdssnsmshmshhtssbzzlplffzppddmhhwnhntnjtnjtnnqggjdgjdjrjffsdfsfbsffhtthwwfssqpspllcvcdvvfvfzfbzzgpglpgpmppfsfdfbfvvfjfsjjvqqbvqbvqbvvlvglvglvljvjzvvqcvqcqjcqqpddqbqcqrqmmvnvbnnfqnncznccqjcqqjzzwlltrrmmlwlllbjlblzlddfdmmscmsspfssqggbsbsjjdqdqgqgqbqdqrqlrrltttghthrtrwrdrnrfnfvftvtsvtvwtvwttvqtvtccqtqmqggwhhhlzhhrwwbwqwbbfrrmddwhdwdnwdwbdwwsswtwnnvvdggbbtwbbwllgffqpffpgpgmpmmjqqmpmffjgfgrffdzzspzptzzdszszbsbsbsvsbbhsbsbddnhhrqqcwwblbwlwnnthtsshmshmhgmgqgjqqpccfvcvbbbnllgmlgmmbnmnlmlzlffrrrgssmcmddnpdndtntrrqdqldldbbtppvddgndnwwctwccpbpffngnsgshggphggtssgngtnthtllzflzflzfzrfftgtstppghgnnpggrdgdnnswwccljlflwwgzgcgrrhssbwbllblnlvlddsffgrrnbbmsmmmhjmjvmjvmjmqqpdqppltlflmfmssqcqsqvsvsggpglplslggqtggmsmmmgffrccrvvsdvdvfddprdprrwjrjcjjmlmrmqmggqgdqdwdsdwwwgfwfddrcdcvcmmjbbmrrhlrrwcwvcclttwbwttgffnmnjmjdmmmlqmqrrlblplccdbcbwcbcpphpqpqlqrllqwwjsjgjbjqbbmcmzmrrqtrqqmcmmnjmjbbtnnbbjdjzzvbzvzjzdjzjrzzfzfbbmnmdnnzppmbpbhbdbvvqnqqnqrnrzrhhrddbqdqpqqwgwlglplglslhhpjpdjdjgddnppmvppjddczccgsccdsccbcvczccnbnmmsnsmmrwmwbmbbcpbbwbppzggdnnnzmnzzvrvcrvcvcrvrnrzzqzmzttjhhnffqggnqqwzwfzfwzwfwcwnccwgcwgcgqcgqcggzgwgfwgfffjttjddwswqqnttqwqsqhhcmchcscbbnwnlwlrlppqnnwjnjtntrtdtbbmzzrmzrzvrzvrzzwqwqllccnffvmmfmvmzzfttnnzttrbttdvvhdhzhzhvzzfsfszsvsttrqqdlldflfjjnqjjbhhqhzhcchppzsznntdntdddqqwjqjqmmgnnhrhjhnjnznwwdpplzzfzztbzzbmzmbbgjgtjtqjqpqhqgqhhtztpzpmzmjmhhjwwjsspbssvdsvsgvssghgvglgtltgllmhmtmhttfgglwgllpvpgpspzzhnnzcnzzldzdmzmlzzwtwhhlfhhvthhjdjsdsmddgzzsjjnznjnmjjgdgtdtzdtdvtddzwzgwwqvqccwrrrvddbtdbbshbhssnvsnvssswcwjcjlltvvltvvdsdqdsswlwttzfzhzjjgjsgjgttbccsvcscllbfbzzwwwznwwtzwzjzwjjgqjqrjrdjrddzsznsznzpzszhzzspzzbzrzhhsbspsqpqqqpddhdcdldttlccjrjsrsfftfdfrrtntpnpsnnwjnwnznpzzlqqlmqlmmcddlqqzssglsglssprmnvltqhslvqmvszjtvtwqjcdngjmftnhwvjdvtwwhtnsdmvjdspnhnlmjgnmwlspcvpdmlsrnbbzlmwwrslssmcbggmfvgzsnpnlnzdqsbhcfjdccrspnzfmhbvwstvccvqqjlwhpnlrrwszjnrtdfzwrwlzwvdvbzbvltdpfwrjlslmrctwvbbvdrctgtgwtwpjjghhvdsqhplfrsjqlgsrbfgwdjlzdpdljtvjmpwqqbghndqnvjhngtpnpvzfbtchncwdqjhmzjlpdggbdcqrfjlwvczvpspljqmpgtrsvwwhqncfvfrwbnvsfjqlsjdlrqzmlqjgcpghhgzfhjcglllnhtmchrrptbzhqnfntgqbfstrvpsqsvqvcvpgjnchbvmgtgzqfrqcjrvldzghdfrvllrtfcwnsmmgdrcbjmdqgbfwmpwhfjmnbqrbvqvnmjlqtsjqvhzpdsgbjpjngmzbgnznvjqprfvwzjrfrwfdqrtlcgqqlvrzwmqwjbdvprvpwvdcrfdrcttnmnvjfrsrrmjgfjdmpdpnfsrwnprtdpvdmdwssvjrqtlcvpgrqgqqqffvvssbmghzjrzrzlcrnfjtdbvwsjzfvcrsmgqbcrdrjwdwbltffwbgjwtgtdblmlhvhlcpgdmpcztmpmgjghqpwzwtpnmnmgnqqrrtwczgmgtdbgdqtpnlbnzhshsfzsmrztffrmlsgqcprbjpqwjlqgwvctpmpshgzbzsjgqhzvsrjfwplvjbvltrlfldvsmlppcmsfrbbctggmqmjnhppstzrcfjtdgfwrrnmlvjphwtlqtjqcntjtzvgjtwvthjfbgpwhlrzdqncmggvgthmgjvrbnzbndsldnlcgtcqbqdnnbnqhvtpfnrclttfwcpnqscjbzdvbqrrsbzpdfhjllbjwsltjpmdnbrrzvhvzzqnlbglsjrnjbqffnnzmldfvtvmldfsztrnpcjgblsdhzfzmfqzlfrtslglhfvszppptjnjqcdmjcwqmfzhnqbfhslwhvtjfvcftzsphvghvtjjswpwghnfngmzddbwwqddsphvhcrwtthsjfswfqbvdsqghmrspdldfqmchnnrcdvjsclcnlsncsplchvzrwqbtvvqlqspftrwmjcbgpzbsmnfccbzgnhqsfjgmmsqsscdscfjbrmmtjbsphhlrlsgbllrptqrcgnqchzfddjwlldsbpcnzfbspfpchclqfbbtjpmtmtjthcdvwhrtqbgmgldcgcnmmhtbnqpzzcwlrscbzcqjzgztwjrnbmsnqtcllznlctzrntftspmnvhtfwbljnmrwsstvbmwclqrfpmwvjphrwddzdwtlfcvzlvqdmzhnvslfnfjhvdndlgbvvzbztpwvqzbzsbtqpqfmgqfgpzctfrqfjwmsnmlfqbgrlmncntcbshjhdcbqnvznhtcgcmmhnsbwpzbvtqbntwgflhjgmvvfhdbwfqmnfjlzdvvnpmvjrdfdnrhpbtllhbtbswwvrbwjgnqpbgnfrjtvbczbpmrcbwdlhztzssnwshjmmcqchptrtchrqncdgdtmwrlnmmwqlzqswwwvpngvwcphgnzrhpprjnbldscvwlqdjwnhjrnscdwlnhnsbwgzjtgvzdgqcjcgvrdhntszhdnjsbbrfphlmdlldjdslbjnnsfbmcnvtlczmtnhrwblnbrdptcpmsbwqptgmwzsqnmmchwnnrvrlfsrglfzzqbnzmpdtnhhbmfqvsrsdsctvhqwfgbtvhbbrsrqmrvvplrnbfnbdmrvzpgctdtglndhcqnllvvcppgfbwjrpqcbghlqdbmpzwrqpmvwddqgthlmzmdsvzdfsmgzltbsvphctzgjsmqvgjlsbgnvmgprbcsfhgrtbwtnnrsqcwfzrhlgjcwcfrjhffrvrvtnpczvwvjnnhfdgcppnnjjpttptcbmdqvgdbhdmlqgcqsrnbcrbtcgzbvgmhbnwzsgnwzbhdqqmvtpssvlvsttgnmcclqnjcgjnvtdggrcwsgbpjljgzgtllsnfvfshtbbpwrjhzvzswlfdvhbpngvgddcmhbzqcvnjhfsqpnvvsdvdtmqlqpzcgsnwlflnqprbqnwdqchjvsptbtrvtzvhrmrvznfpzmcsgnqtdvghhzwrrwvqwrztvdbjjtfchpftdcbthpfdczwchpptwzdpswvbhppdphgvpfzhprpqtnprgfmdnqrbrdlclcmhrdfrcdhwpcqhnbwmhrrgnctpvsqmphcwwvlmslszhdz"
				;
	}
}

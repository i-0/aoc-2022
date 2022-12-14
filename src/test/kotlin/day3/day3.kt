package day3

import day3.Decoder.toCode
import day3.Lookup.findBadges
import day3.Lookup.findSharedItems
import day3.Parser.parse
import day3.Parser.parseIntoGroupsOfThree
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

// ----------------------------------------------------
// data
// ----------------------------------------------------
typealias Rucksack = Pair<String, String>
typealias ThreeRucksacks = List<String>

// ----------------------------------------------------
// algo
// ----------------------------------------------------
object Parser {
    fun parse(input: String): List<Rucksack> =
        input.lines().map {
            val first = it.substring(0, it.length / 2)
            val second = it.substring(it.length / 2, it.length)
            first to second
        }
    fun parseIntoGroupsOfThree(inputSmall: String): List<ThreeRucksacks> =
        inputSmall.lines().chunked(3)
}

object Lookup {
    fun findSharedItems(rucksacks: List<Rucksack>): List<Char> =
        rucksacks.map {
            val set1: Set<Char> = it.first.toSet()
            val set2: Set<Char> = it.second.toSet()
            val intersect = set1.intersect(set2)
            intersect.first()
        }

    fun findBadges(rucksacks: List<ThreeRucksacks>): List<Char> {
        return rucksacks.map {
            val rs1: Set<Char> = it[0].toSet()
            val rs2: Set<Char> = it[1].toSet()
            val rs3: Set<Char> = it[2].toSet()
            val group1 = rs1.intersect(rs2)
            val group2 = rs2.intersect(rs3)
            group1.intersect(group2).first()
        }
    }
}

object Decoder {
    fun toCode(it: Char): Int {
        val offset: Int = if (it in ('a'..'z')) {
            -96 // non-caps offset
        } else {
            -38 // caps offset
        }
        return it.code + offset
    }
}

// ----------------------------------------------------
// tests
// ----------------------------------------------------
class Day3Tests {
    @Test
    fun `part 1`() {
        val inputSmall = """
           vJrwpWtwJgWrhcsFMMfFFhFp
           jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
           PmmdzqPrVvPwwTWBwg
           wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
           ttgJtRGJQctTZtZT
           CrZsJsPPZsGzwwsLwLmpwMDw
       """.trimIndent()
        val parsed: List<Rucksack> = parse(inputSmall)
        parsed shouldBe listOf(
            "vJrwpWtwJgWr" to "hcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGL" to "rsFMfFZSrLrFZsSL",
            "PmmdzqPrV" to "vPwwTWBwg",
            "wMqvLMZHhHMvwLH" to "jbvcjnnSBnvTQFn",
            "ttgJtRGJ" to "QctTZtZT",
            "CrZsJsPPZsGz" to "wwsLwLmpwMDw",
        )
        val shared = findSharedItems(parsed)
        shared shouldBe listOf(
            'p',
            'L',
            'P',
            'v',
            't',
            's'
        )

        val codes = shared.map {
            toCode(it)
        }

        codes shouldBe listOf(
            16,
            38,
            42,
            22,
            20,
            19
        )

        codes.sum() shouldBe 157

        val rucksacks2 = parse(input)
        val sharedItems = findSharedItems(rucksacks2)
        val codes2 = sharedItems.map { toCode(it) }
        println(codes2)
        val sum2 = codes2.sum()
        sum2 shouldBe 8153 //solution part 1
    }

    @Test
    fun part2() {
        val inputSmall = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()

        val parsed: List<List<String>> = parseIntoGroupsOfThree(inputSmall)
        parsed shouldBe listOf(
            listOf(
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg"
            ),
            listOf(
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw"
            )
        )

        val shared: List<Char> = findBadges(parsed)

        shared shouldBe listOf(
            'r',
            'Z'
        )

        val codes = shared.sumOf { toCode(it) }
        codes shouldBe 70

        // part 2
        findBadges(parseIntoGroupsOfThree(input)).sumOf { toCode(it) } shouldBe 2342

    }

}

val input = """
VdzVHmNpdVmBBCpmQLTNfTtMhMJnhFhTTf
FgqsZbqDDFqRrhhJnsnLMTfhJG
bRRRPrRRwSwbDqgjvDZbRbQzpzmQVWCzzBdvQBFCzlWV
GcDdRdvhRssRhGDdShCRtqWjlQzqWgqzNfNjfQWWjt
mwwnnPFwmVrPmJmzfNzqCjQCbgVlgC
nPnHHLrHwmJTrCTJpThBscBSdSLGZvZBvRhZ
RVQQcVlcSRclfZCCCnMJJTSTnC
NdHwjdwjbBBZrrZrbJDZJJ
wmhjGGBGwwmjtjtdPlfRcpVQlhRppVJF
pplbNBPPrppllrFNbpvppSTcwqcWFhTTShhJDTchqd
RGzRfLjjmZmfmwLftTWhStStJWTdWmDm
nfsMjQssnpPvNnrPrw
SjjBgllzlQjBZvlBBgcFbgJHsMhJqbMHPggJbM
hRLRVDdRRWnJqnnHTqMCnH
GRfLddRRpVhNVrWSjwQQzSzcGSBQSc
qMwNqqBdQdnTVBBVVhMVnVFzTHPggTPjGRDzvPTjjmvPDj
sbSrWJpStrtPtRPttzmmDD
pfbJJcbsrcLpWLllsnFmcqcwQncnQQqVNQ
RBTWCMwCwdZThPZcZZ
pVmVpHLFFFHHVgVmvNmHSQNvddlPPzZJMPcdhclhjczLdZMP
vnnNnFStGMRDwWnn
fWDdJTpDJzdBBBdmDSbSRHRwPqbPbHgSbz
slQtQvNsMVvrrgPRgRglnhwWPH
sGMMQFrsjvNMfWmdpfFDFZBf
vnMRMWCMJwWWwwWPjmSdVmLdzvVbLrhL
HsNfDHQlZpNqfQzbLbrqhjLmVdjd
dfDZQsNpstHHHptZDDtZWgngtgBMPMMRwCPtBBGW
HwQwwbwFNWHwHBVFQFLQzRznZnSzcjjjpPbcPpSP
vTfTJsCmsftJZmTSSdPvzdjRSvPdjd
TrGtTJfmGDfDhrhJJJsqrZhDBFLHHLLQWFwwlWBVBBVwgLFD
FFTJRLccQgmTbSsbGm
PBPPqCvCwqwhQQVhQngmVmSgglmGnHbnmb
zqthvtQPBfCCzPwQPtwQzPwNLfNRFNLdLRLFRFFNLFdFdW
nszjQnsPwjznzCCrhJqvjqhmBv
tFWdHGWFGtctlNNpZBBhmqTrrbWqvTBT
dlFtcpHDVVVHFdNGHGpGfQgsPDzSMsQwwPwgLLBQ
TzQqTJGvnnSzqrWTnvfbbcflQcVltfcCMPVM
jFjNZFFJLpFwmBwblcpptcVtfbbVlR
jmmJdBBLNdGDWDDrdzqn
pzddqQmGgbqgGpbJmmdnLZDCRZnZvFlLRZLSlLRT
rVwchcBBMwVBHhHTZCTSGSCRTZlTDr
HtccPfjfBhMtVBGHWpNqJdJdpjNJppWz
WThTWWhtPbZRvvWbbvRTSRMjVRLLgFssgLpVsfSF
JdwrlJcCwfzdqwwjsjzpLMgVsMFgML
lQrwHNGJHClvTmfhBmPQmt
lbRLhcLRpLJzgdGddF
qvhwqDDCVtBDVhfMVGFnzGGzTBnGzGGgFg
VjCwCWCMtjVDtChvQhtffcSmHpNWrrcHZHHZpplWbp
DJVDVdvpmZdPgrCbgbgCJC
lzczcWwwznGhBgPSvTlCrNgqNC
wGzzQhzGGsBBGRBcQwGwnwjmmRHpRfmmMpppMjjHDvLL
HJjJQWjFmmWtFmJTMchghhDwNMhVMWML
SznPSRfRSSPdrrPSShbDVhbLPwcwGGwVNh
ddRfzdRrCrRsZDSnFjspvFvqFqFqTvJt
lflfjQfjvljfbfMLTTDCmHNLNVbL
HSJnRrrJZJssnGRrnsrcqqRnDCLBMhVCTLVLhVNVJBBBhhBm
SsrGGqqnSsWSnnqWHSrPfzftvFdvWlwfQgQwWvzz
nQlsGnFGwwqNJWmJJjpplt
HMTLPTRdvsTCCThDCZdLdLDNNpJBWJbjJMpBmbtNptBWmm
DLCzPzTzZDdLdGSGfSGrsnQGzr
LNPPLHNPHQNQSBFDWDPgggFv
hszfWCWJhrBMsSSBgvFD
GGZjfmJTjmZfrJrZrZJRGwNQnlLNHWjLVjlwdVNHpV
BdNVdTcGVclmTwrTnwPwrHCr
zttBWzfLsCggHPwDrf
szsWSMbWzzbqBbzJjtjsvMzzvdmdVpGllpcRNZZhmRpZcGGc
CjdbMmmmZFnzzgHlttGBVqtBGtsldG
LvPPWNcFSSRslWhBsllT
ppccvLPpcSNwLLwrDNNpLvwJHCMDmbCJbFzgmZZmFgbgnM
TTNRwZqhcTTjsNTTsmrJlvrmmmqqHSrlJH
fLQCCdtcfCDDVbVVQdFbQbdJHMHrJHrHnMllHdMHPrMdln
WfQLQWWDbwRTWcRssN
HQGQWHPDHNjMNQGNWNTWCvZllzqFZqzvvzhCtvFj
DfgwdgfcFpchztvt
sRggdwwVdgmnSTnnDBPBNWLn
WbCZCfTVTTJjSwGdWNDGGw
MMRqggMsqhlmlhrssHgRnRmRvdzdczvdNGNLzScGDrNzrLNc
lRqsnRhmqqQnQpgQMlgDqRfBTJVFbJZQtBCbZQJVZFFb
JnhQcCnmLDsmgmgr
bbMZppRFGGRPfBMMRGMZssTTrLlLfsLlVLdsLsdn
GZGSpPGMZtGGPFFRGBCwhvwjjcnJctvQcvHq
vvrPrHZMGJNRMnqn
BVChWWcDVWsBwCWwGrJNhRLJJnJtLqnq
cjDfcfpWWsfWccBsHgPgrPTdpZbbgggv
GshtVtVtjSCVtVvVGtlVvFZLMvLRZmHmZwbLwZdLdZmR
JWzNDQzjcgJgQBJgzgMwLLHZZcdPwRLwRdHZ
QWTppBWfDrrNBTTfffhFCpVSjnhCGsFtsqSl
nmbCnzHHNzCjCJHJNSCWHLBLrvBrrSGRBDhrDRLrGL
TVtPllwcgdmTRhLQTQhT
fdFtccFcpPmggfdfNzHzCMsbCnWnJs
fMgddvjgRRvjvjVJVdTlZGGtGnrlnqTccNjl
HHSFSWSmmpbBpZlGncrNGbNtrn
WDWBDDBDBDCwPBWBDWNQDgzvVvLRvsVLRwvwdJVLwL
ZSmmvcpsmcJmJvqgBZgZqqtCtZjl
WhDwhFSDgtBFjnFg
rTrSTLWTTHNMNwNrMVddwNNhsmJGQcRsRcJGsJzQJsrzPsPm
GBtLmPsCQqsGqgghZHDzzgLbFz
zjjVTzTlRjRJfznrvrfpnNhFSghbbNFgHrbHZbDHbH
vpfcTJVpcVlfcQPMPCGzCBsd
HMhZNffcPZfNMrzjjFdGcJDjvJ
VSBVVLlSQQmTVSWpSQzDrHzTTvDvFjFdGGzT
mQSplVHWbHLSgWQnShNwsZMZPfbsNCRNCt
MMqvDzLwZzlMqQfdGWPfgPffPglH
ShTcJshsrRdnrdfrrfHp
VVRtFhsCJVJVvwQqDdbDQd
dmnNMlFNvmvljnbpMWNDFQvfQJJGvfPCfHGgQQgcJg
bRVzLBSSTRBRBBrwTrVtRwCfcGHcsJgJgzgGsggHCzcC
ZbtVTTrrqrSSVwhqqwBRwFWMDFNdjdZpWjdDDppjMW
MTzqtbLtwFzJgbHgfbdWWH
VMNBjNVjvNfhhhhfNPhP
jmGMvlDZZnVMtzlwzqqCpwFt
PpzGspGmpPsFLrTnTLzzBg
QCWvfjfWjRPFZgrvqrBvTg
wwNRCNQQVNRWjNWfQbHCCClHGDGJdGhpdhtPGhltDlJD
dhbpGzhllzGlPvnzNcvtNVnc
gcFMsTJDMMwrZqfjjqvvfnPtqJ
sWRWTRFwrTgLDDFWgMsTlpSlpbSCdWWdcbmpChGd
QccdFFFcFbcQPQPHMgpPMp
NJlNSSMLDfJfmlSqHZRNpRqNBRPRPq
LlMmJfvDVVTJSmVMscsCFtvwcjWjrjCj
NVVMGWFSMRVGWSthwhTJWzcJCcJsTs
jqRLqlfRZcmjcCzT
rlRRrdrflpdvPbHpflfPlfDBgBMQpGVQMgpVDGMggBDV
VwRhccRsnQStRhtGQVQVsmjgDgqJdggDjqLDgJlLzmLl
BWFZpWHBNCBCNBzBNvWBpzHZqqlMqgNdlllDdqDgJDLlfDdd
pFbTrrrBzbzTtSwStQnnsrVn
DRfFbFqzbddfPFtsJnJRsnClJRsn
cgjgQgWvSLVQgmWWgWVjVSSSBTltThLnqJssnTCZsTThntZT
qjwpSrmWgcSrGMfdFDFdwHFd
RWjDDWDjDNjjgDtSRRgjcjzFpnzwdFbFNdbFbpnldwFF
vQfPfTQJbZdThTzL
PrBQJQsfQqrrbfmPqMBfJbggjRVgWjttsHRSgRctDjSs
NgqNWqqWWdnJdqpBNFtCmJGCDHttDGDsHsHm
BjvzhRLTrTBQhTMQRjRRcjPGtmDCZZDZSCmmMSSZmVmSSt
vQzRvRzQcPcvfQzRnddppFgnFfWwBFlb
nnPvfvgrtPDHgvvGTRRRPZQGpGCLLV
FlBsBdbllFdfWpbGMCVMZLVbZQ
lhchNcqFsJBlBszztvwHjvzgrzmzffgH
zZhdjTpJJpjmmpPZhvqnnZHqZcggvgMbgv
tFpFQFSFtBGlFNwFfNMnHfbHcnvcvcfvcqrM
GBFlNLSNVGVSSGtQSLLBBlNtphDdzpmmPmTPhRmdzdVCCDdR
rpRCCDLpmnCdJCjn
vMhSFvgsMGLmnmWMmm
wVqFFvwvPPHhFhhgHPwHshpqrDDzqlfRbpftRLblrllr
CRNDzdJCVDWzVgDjdjzRJzWRMTbHsMNZNbZMMbsfhTtMTLMB
wSlwQcSpqPpcqcqFSqpwslsTfZtLhtlthtBHtTMZ
SPGFGFFmpcPGDrWDmjDJVffR
dsmdtJthJphWqHRPnRRsvvnnfR
cDBMDDDlBZglDZTMDfzVvNRrvNPVHzRRTV
PGMCCDClBDDbbFqmmhqQdpWGmmWp
BJjcGhcvCnBdGHsmHSzZDzSDMHmRMQ
qLWPLVrTwWlwwwrfrFfGDNmDQRMbQMzzmmbQLNMM
rVWrFlGqlqwVwVGgWGphnvgBBsnvsjdBnCBnBg
sNNsfBsmcGmgNTcHHSpnTWHnpV
QlrhlrlMglhDQrdFblvFtMdnDWwSHDWWwnTSjLwVDSwwwT
QtdMvltZhbFlPPZbQtQthZQdqCsJJGzBqqCBmCNCqgRCBsfP
SZnQnnHRWRQRVjHnqlJTQPfdlqfJftqG
pDzmbDBFbBLvvzttfdlTTl
gsDLLpcmsSZVwlnRsV
LHsWjwjWqCLsqCHcLsjdLqcdbpMGZPPtBhthbZBpBhMllwPG
VFnVbbvJSfbgphSpGlhRBBSP
JrrTgmFgzvNbrmNnmnvzgTLjCQWDLDCsTjssjqcHLc
QmwwqTqsrdqNNqgtvnVDVcGNNtvv
WBFBpzzjSJBJzJbfntgPzVzcvPnzDf
HcpbHZJBFpjpcSZrZsdRQZrCwrwd
JqmLmbtTWThBTWvWGVSrrVDsSGSG
wwzRzNjNNbsPVPds
jfgQRZwpQclQfffHgpRpwpfTcqtLLqCbbFFFLmbmTTBnFB
fGpcccNNqcctqGMprvMPmbbzFSflSRzPBBlBbS
JCjnjTZTTGPSGmTFPb
ZWHhJjHLDVDgHLLDGjnhctsstwqctNwWqNwwQrtv
sDwQhcwhBDDwrhGsQnRBQHHMHHMNJMZFCFRbCRftMM
zjjlmjqfdTqlWdzTqmLzlzVjNCHJNHNFMFtbJNZgVNMMCCtN
vPTfLmPTLWBsPDnSscnS
ngznwDPPTzhPPDCTQnTTDQBQqHNNrHFVppbbjRFFqFhHqRqr
tZJtcGsGtLLcctRqVBbbqrspbHNq
BmZSvGBMdWPzMPgnnz
MpNWPVNWWZWVVNZHVcvJjgBjJMStMJSjjg
rzdCzrCTTLRCslvJDSjjdScgDm
RLhCQzqTCssThRQzRzwGQrrCFffbfWppNpWNWVcHqZbHpVPp
zQzCVWdSSjCdjpchWcGftflGZcgG
RwnJTJwmvFHTBFmtBccZZfBGMstllM
wwvvHRwqDnHFrmqnrSbQVVQfSbqQjbqjbQ
ttDftStSlftPgSHmJbFwnMnFwzbrLHMMzz
GqTBqhBqBvppBvMMTznrCbCnLwfr
RBjjpZZvvZqGcNhjjpNmDPfDcsgfDfgScsQQQg
rsSFccvBHppHPsvQrSHSprFjnbLGdbzLfbGLLtLjjzLzvl
JWWJhmwwTDTGtnzlhdbtLG
CWNqWRNCwnCJVppQFFFFNrgHBB
MSRVnMjnVRVnPlcsrtMtschgDl
NWHBwJBwBBQCHHqwWQGBNgdrFFtsthcqdltdDsqttq
CCTTGCNCCBfNJNNWbGGnvVzDSRfDRSZvLPSzRn
MpRfjRjWpZzzzRzZSpjzZjTCQcGdHLWNGqdBdcBWWBLccn
lrbrsPQDPQglDtwggcLCqnCdNNdHBLsqNd
blwbJggvgbwlvQbvtgwmvVwRfTzfMMjFVfSFjZjMTSTSzj
ttSGjHWVrwWrWWvhzvhmhDfR
qMBdNNsccQgfDRzRmqlhRl
gQJdfJPdQBsMggMjPrTCLjrGrCrtVT
tGFdlwDwGFdNtStghWWdQFSnTVfCfZhrfVTVCVprnRRhVn
cLsBPQJsQPmbmPHTnRRnHprCVfns
MjmvPqqQjPbQzjLwwDWDSlzSlGSgwl
NSCpFgfbscbZZZwrtgPZJT
zGCQlVGmmQGVqqJwGtHZGrPHHRTH
qQvVmvzmqCdhhjzCQLjljLQMnMDSFWcSfnMfpbfnNcFFbFDN
zFgqjQBmWNlWlfHrHdLc
wnbCpSSZZTJSJSnmdrtHfGtftlcpltpH
ZSwhVPPJgNVmNFzs
WNVJthVHRRfLqpqN
gdCGcCgJBCrgScRLzbjQQLfRRR
SCFdGSFvlhTJsnvW
FFZwFZZwRmFFhHtNLNLGRtsqjLMt
gbDnnrMbMCffMPbPLNjGNlcppNtspp
rgbzrzDrgVgnrBzFWMWmWBwHWHShSB
zjRVjDqzRjvSBnBGGsfsrFsV
fLccLLZpJMctwJWWWJWpJGCwFwsgnngFBPsCnnTBPT
LbJlZNWMtpMlHRNHzdfSDfdj
VGbbnJGSTsVTssTTnVVWMtfBBmvftRHfHBMJJfZp
ghqtrzgPrjdzQCjmZMHfRHZHBmQmmB
gltFtDqFVlTVWlTl
HqNqZDTvNvVTLPSTvzfrfHfdndffwnbdnwrH
MpSJlFcMJmcpFlmClcMcRnWbWtthrnfwnCGrrWfrwC
RjcJJmSFMRQpMRFjMNVvTZjNPPvLTBPBBB
MzClDtlzJzFzNGGm
bjcLRHlTBsFJGmRm
HcPSSfTSpLZLbSwtrtvMnlDCDPCl
gWWgQJCsVhgRLCWsdjpmcBHvfvrrnvCvBB
TqDtztqtStlbNTPtllqZpvmcFHjNjvjNvHvmrrmj
ztPPGZqTPSbJgchGgwRQgQ
wVrdtTqtCCvbNgbNTTDN
mhGzWhGzMGWGrRmbFLBHZRNHNvZvgB
hhGhShpnsSrqVCVSSj
HnlbmGnlHZHnlBcjgwfDVfwLsGLGLDgR
WhWMWTvQPWPLDMFRCDMsVD
QdzJQPSPZqJnJRnZ
TTjTjFBcRBGjwsDTBLmrCftfRVrrCftCVNRP
WnqbJWnnQJhSqVfVPfDnggfrVN
hSlDMllvhbQqllZlSWQdSQTBjsFHBjTwGdHBTBszLzcc
rNWqWDLZWcqFqLLLgQQJnndnQdNzzJVMzd
cPtsPvChtRsGswHPGbwcPcdVnpzvnmBmVvJBJdJVJdzn
PfRfRGtsHsSRftbbbbHhwCCsjZgSTgSZWDTcgDZjLqgqFWLq
BNzPnPJNNMwHJRhBGRWRdjFQddFlFjWd
npbZrgnLSCSrWFjjdlZGlcDF
gqtmmngSbmgHJqfqzNBHBJ
stgzttBPRRRdpSVVpdpS
WJFcLQmJZHcCFLJmcZLMfbpGSWMNpGftSSpMrb
mcvvQvHmtLCJmHZQHZHCDHJJnjqPjjPzwvwhnwPqTjPBzPnB
DDmbbPqgFSbSQPtPQJttrltJ
CRfcnZWmRRhJNVtsVnQlsp
fcBvWvWzcZWCzTTCTTvccFMBHGDdSwGFFbqwFSGSmF
tCRBPCPRjzsJszBmtjmCvSpHcppJpvZdHHHcHZJG
qbrlLnWlQDQDNvmHHHHcrZZvdm
nmnWnnWmgQsCFzFCRVCg
sHMHCDZfcwMcRcLMcZDCRCHMPdJqgjvVdvqgdgfdJbQgvWQb
FFhTzmzGrnmtjTBjBBprrmFtqGgQqvVdPbbgqQQWJvvWJQqV
TrNjrnFSlwNZNlNL
JQGdsdzSzsdFQFSdssnndNlZjNPTJZNljVjTPhVPhT
GgGLfRmHGLhNVjjNTLhV
HvGvwpbHHRwpBrvBgSSzSFndtzndCrsFMd
DptFshMrhDhDwmPPhwSNhmmS
RLdcdRvBjnvRVcvlcLbCcbCwwpGBwSmfZqqPPPPwzmfqZq
VlRLvVjCJLnlpvvRdllLcJJWDHgMMHDDHtWFFDDQgH
SsSdrndpDlCdLftd
VGPVJgmQrVGHHZfwLlfCTmfwlDlT
PcJHcGgcWWbJpjRrphRbFpRn
PdPSMHMLzPPSShBdffMMzMRHQQrpppcqTCQQpCccTGTRCnCQ
vsbWmFbmJmZFFsmsbfpGVrGnWrrpVpnnVcTV
JZslstfZNNSSPdlSMwlM
bLLzRzZLbRqJJrDGGVZdwssDvGQw
FCtNJlTFtmPfldSvDvQFVVsjSv
PmCPHBhhPpWLWgzgHJ
qwmwFHCgPgPPqPwMCrHHFBVVRBttVRRffVfmsjVNNB
WSvcSnvbSWbhcbjlQbvlSQhlfBGcspVVsVGfVscpzpNcpBtN
hJSLhlvlTjPFHMLCCq
bggDpTggncGVVWbQcG
sRvSwwwFBSpFzvRvMFZqmPmMVqmcmPPVqhqqWq
SRBBrFZZwrddBFRjlptnLgDnTggdtd
PPfMcZMflbMQcMllPVfTVMwjWWmZvpWWpWhhjjpdWWww
sDQNnzsnQgDNsFzFqtGjGmWWSpWrGhdpvphdSW
nNQNqLBHLqzDnHgnVJfPJPCRBbfCcJlT
fppppWsjcSDPjjDpGhgwbfTgHTCbHJwbHbTR
rLBdQnvMNMmFPbLqHqTqgLHCgL
nrFznvMNMQdttrBcScsDstWcPGtWSc
lclnRSDnGZtvSwnZDZzhLffqdsCNwTBCBBdNsd
mPjmjmrFFpmQjMJQjlNdlhCsCLsTlNLs
ggmrHHVVQVPJpWrgpWScRvzZzGGRnZvlgzZn
GjGJGQJGcMTVfFDQzNVQzP
mHqdbmmdnJqVzVhRVNzPbR
wStmHJsJsLZLjTvM
QssMbVGdMQjZPjwVwHVZPZClllvgSgvlTgwwSSCgSCtC
WmmFBmJrcFRBFrJJBFchzWCStgCTgvhvTlfsNqfTlvTv
sFzzssDLzrBPjDVVddHMQD
fztDZSGrNrlnbnPTgFFpln
jvvQMMcLcjJmQwHdJvjQJnnbTbRFRphnnpsWgmFRPR
vTLHHCQLHBBjJCSZrVCZtSfSfrft
gHfHffHLjwHrRjLrLRZVMnTdTBsNTBwTVBsBnN
DWPhqhhDhvSGvWPzSzMBQBQVMMBBmvssvQvQ
CDGbqCDbChSbWGrHcHRgbcVcfrLJ
frlTLmtllbbbdpJS
qFjhzjThjHTFGHTjqhhjMzBhVpVpdbBnSJQRpBnVVdbRRQJd
vjWPWjWPPPWgwmfCrNvTvZ
""".trimIndent()

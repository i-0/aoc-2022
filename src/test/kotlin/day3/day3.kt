package day3

import day3.Parser.parse
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


// ----------------------------------------------------
// data
// ----------------------------------------------------
typealias Rucksack = Pair<String, String>


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
}

fun findSharedItems(rucksacks: List<Rucksack>): List<Char> =
    rucksacks.map {
        val set1 = it.first.toSet()
        val set2 = it.second.toSet()
        val intersect = set1.intersect(set2)
        intersect.first()
    }


private fun toCode(it: Char): Int {
    var offset: Int
    if (it in ('a'..'z')) {
        offset = -96 // non-caps offset
    } else {
        offset = -38 // caps offset
    }
    return it.code + offset
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
    internal fun part2() {
        val inputSmall = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()

        val parsed = parseIntoGroupsOfThree(inputSmall)
    }

    private fun parseIntoGroupsOfThree(inputSmall: String): List<Rucksack> {
        TODO("Not yet implemented")
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

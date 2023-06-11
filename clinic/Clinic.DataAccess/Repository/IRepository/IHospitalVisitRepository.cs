using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IHospitalVisitRepository : IRepositoryAsync<HospitalVisit>
    {
        void Update(HospitalVisit hospitalVisit);
    }
}